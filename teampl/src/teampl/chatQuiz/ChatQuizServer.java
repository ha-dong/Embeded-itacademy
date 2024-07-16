package teampl.chatQuiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatQuizServer{

    private static final int PORT = 9999; // 포트 번호를 상수로 설정합니다.
    private static int MAX_USERS = 1; // 최대 사용자 수를 설정합니다.
    private static final int NUM_QUESTIONS = 5; // 문제의 수를 설정합니다.
    private static final int WAIT_TIME_SECONDS = 3; // 대기 시간(초)을 설정합니다.

    // 클라이언트에게 메시지를 보내기 위한 PrintWriter 객체를 저장하는 리스트입니다.
    private static final List<PrintWriter> clientWriters = new ArrayList<>();

    // 개별 클라이언트에 대한 소켓 연결을 나타내는 Socket 객체를 저장하는 리스트입니다.
    private static final List<Socket> clientSockets = new ArrayList<>();

    // 각 클라이언트의 사용자 이름을 저장하는 리스트입니다.
    private static final List<String> userNames = new ArrayList<>();

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver"; // JDBC 드라이버를 설정합니다.
    public static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; // JDBC URL을 설정합니다.
    public static final String USER_ID = "system"; // 데이터베이스 사용자 아이디를 설정합니다.
    public static final String USER_PASS = "1234"; // 데이터베이스 사용자 비밀번호를 설정합니다.

    public static void main(String[] args) {
        Properties props = new Properties(); // 프로퍼티를 생성합니다.
        Connection conn = null; // 데이터베이스 연결을 나타내는 Connection 객체를 초기화합니다.
        ServerSocket serverSocket = null; // 서버 소켓을 초기화합니다.

        final AtomicInteger currentQuestionIndex = new AtomicInteger(-1); // 현재 질문 인덱스를 나타내는 AtomicInteger를 초기화합니다.
        final Map<Integer, Integer> userScores = new HashMap<>(); // 사용자 점수를 저장하는 Map을 초기화합니다.

        try {
            Class.forName(JDBC_DRIVER); // JDBC 드라이버를 로드합니다.
            conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS); // 데이터베이스에 연결합니다.

            serverSocket = new ServerSocket(PORT); // 서버 소켓을 생성하고 포트에 바인딩합니다.
            System.out.println("문제 풀이 방 번호 : " + PORT); // 서버 포트 번호를 출력합니다.

            List<BufferedReader> ins = new ArrayList<>(); // BufferedReader를 저장하는 리스트를 생성합니다.
            List<PrintWriter> outs = new ArrayList<>(); // PrintWriter를 저장하는 리스트를 생성합니다.

            for (int i = 0; i < MAX_USERS; i++) { // 최대 사용자 수 만큼 반복합니다.
                Socket clientSocket = serverSocket.accept(); // 클라이언트의 연결을 수락하고 대기합니다.
                System.out.println("플레이어의 접속을 확인했습니다"); // 클라이언트 접속을 확인하는 메시지를 출력합니다.
                clientSockets.add(clientSocket); // 클라이언트 소켓을 리스트에 추가합니다.

                PrintWriter out = new PrintWriter( // 클라이언트에게 데이터를 보내기 위한 PrintWriter를 생성합니다.
                        new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
                
                BufferedReader in = new BufferedReader( // 클라이언트로부터 데이터를 받기 위한 BufferedReader를 생성합니다.
                        new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                
                ins.add(in); // BufferedReader를 리스트에 추가합니다.
                outs.add(out); // PrintWriter를 리스트에 추가합니다.
                
                clientWriters.add(out); // PrintWriter를 리스트에 추가합니다.

                String name = in.readLine(); // 클라이언트로부터 사용자 이름을 읽어옵니다.
                userNames.add(name); // 사용자 이름을 리스트에 추가합니다.
                saveUserToDB(conn, name); // 사용자를 데이터베이스에 저장합니다.
                userScores.put(i + 1, 0); // 사용자 점수를 초기화합니다.
            }

            try (BufferedReader reader = new BufferedReader( // 파일을 읽기 위한 BufferedReader를 생성합니다.
            		
                    new FileReader("D:\\embeded\\eclipse_workspace\\project\\src\\project\\chat\\Question_Quiz",
                            StandardCharsets.UTF_8))) {
                props.load(reader); // 프로퍼티 파일을 로드합니다.
            }

            List<Question> questions = new ArrayList<>(); // 문제를 저장하는 리스트를 생성합니다.
            for (int i = 1; i <= NUM_QUESTIONS; i++) { // 문제의 수 만큼 반복합니다.
                String questionKey = "question" + i; // 프로퍼티 키를 생성합니다.
                String answerKey = "answer" + i; // 프로퍼티 키를 생성합니다.
                // 프로퍼티에서 질문과 정답을 가져와서 Question 객체를 생성하고 리스트에 추가합니다.
                questions.add(new Question(props.getProperty(questionKey), 
                      props.getProperty(answerKey)));
            }

            // 랜덤 객체를 생성합니다.
            Random random = new Random();

            // 각 라운드를 진행합니다.
            for (int round = 0; round < NUM_QUESTIONS; round++) {
                currentQuestionIndex.incrementAndGet(); // 현재 질문 인덱스를 증가시킵니다.

             // 무작위로 질문을 선택합니다.
                int randomIndex = random.nextInt(questions.size()); // 랜덤으로 인덱스를 선택합니다.
                Question question = questions.get(randomIndex); // 선택된 인덱스에 해당하는 질문을 가져옵니다.


                // 선택한 질문을 리스트에서 제거합니다.
                questions.remove(randomIndex);

                // 모든 클라이언트에게 문제를 보냅니다.
                for (PrintWriter writer : clientWriters) {
                    writer.println(
                            "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    writer.println("문제: " + question.getQuestion());
                    writer.println("정답을 입력하세요.");
                    writer.println(
                            "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    writer.println("모든 플레이어가 문제를 풀면 다음으로 넘어 갑니다");
                }

             // 모든 사용자의 답을 대기합니다.
                CountDownLatch latch = new CountDownLatch(MAX_USERS); // 사용자 수 만큼 CountDownLatch를 생성합니다.
                for (int i = 0; i < MAX_USERS; i++) { // 최대 사용자 수만큼 반복합니다.
                    final int userIndex = i + 1; // 사용자 인덱스를 설정합니다.
                    new Thread(() -> { // 새로운 스레드를 생성합니다.
                        try {
                            String userAnswer = ins.get(userIndex - 1).readLine(); // 사용자로부터 답을 읽어옵니다.
                            if (userAnswer != null && question.isCorrect(userAnswer)) { // 답이 정확한지 확인합니다.
                                synchronized (userScores) { // 사용자 점수를 동기화합니다.
                                    userScores.put(userIndex, userScores.get(userIndex) + 1); // 정답인 경우 사용자 점수를 증가시킵니다.
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace(); // 입출력 예외가 발생한 경우 예외를 출력합니다.
                        } finally {
                            latch.countDown(); // CountDownLatch를 감소시킵니다.
                        }
                    }).start(); // 스레드를 시작합니다.
                }
                latch.await(); // 사용자들이 모두 답을 입력할 때까지 대기합니다.


             // 대기 후 3초간 대기합니다.
                for (int i = WAIT_TIME_SECONDS; i > 0; i--) { // 대기 시간까지 반복합니다.
                    for (PrintWriter writer : clientWriters) { // 모든 클라이언트에게 메시지를 전송합니다.
                        writer.println("남은 시간: " + i + "초"); // 남은 시간을 클라이언트에게 전송합니다.
                    }
                    TimeUnit.SECONDS.sleep(1); // 1초간 대기합니다.
                }

            }

         // 사용자 점수를 출력하고 데이터베이스에 저장합니다.
            for (int userId : userScores.keySet()) { // 사용자 아이디를 기준으로 반복합니다.
                int userScore = userScores.get(userId); // 사용자 점수를 가져옵니다.
                String userName = userNames.get(userId - 1); // 사용자 이름을 가져옵니다.
                updateScore(conn, userName, userScore); // 사용자의 점수를 업데이트하고 데이터베이스에 저장합니다.

                // 모든 클라이언트에게 사용자의 점수를 출력합니다.
                for (PrintWriter writer : clientWriters) {
                    writer.println("플레이어 " + userName + "의 점수 : " + userScore);
                }
            }


            // 게임이 종료되었음을 클라이언트에게 알립니다.
            for (PrintWriter writer : clientWriters) {
                writer.println("게임이 종료되었습니다. 결과를 확인하세요.");
                writer.println("만점은 " + NUM_QUESTIONS + "점입니다");
            }

        } catch (IOException | ClassNotFoundException | 
              SQLException | InterruptedException e) 
        //IOException   파일 또는 네트워크 입출력 작업 중에 발생하는 예외입니다.
        //ClassNotFoundException   클래스를 찾을 수 없는 예외입니다.
        //SQLException   데이터베이스와 관련된 작업 중에 발생하는 예외입니다.
        //InterruptedException   스레드가 중단될 때 발생하는 예외입니다.
        {
            e.printStackTrace();
        } finally { // 예외 발생 여부와 관계없이 항상 실행됩니다.
            try {
                if (serverSocket != null)
                    serverSocket.close(); // 서버 소켓을 닫습니다.
                for (Socket socket : clientSockets) { // 모든 클라이언트 소켓에 대해 반복합니다.
                    if (socket != null)
                        socket.close(); // 클라이언트 소켓을 닫습니다.
                }
                if (conn != null)
                    conn.close(); // 데이터베이스 연결을 닫습니다.
            } catch (IOException | SQLException e) { // IOException 또는 SQLException이 발생할 경우를 처리합니다.
                e.printStackTrace(); // 예외 내용을 출력합니다.
            }
        }
        List<UserQuestionTime> userQuestionTimes = new ArrayList<>(); // 사용자가 각 문제를 푸는 데 소요된 시간을 저장할 리스트를 생성합니다.

     // 문제 풀이 시작 시간을 기록합니다.
     long questionStartTime = System.currentTimeMillis();

     // 사용자가 답을 입력한 후에 현재 시간을 기록하여 소요된 시간을 계산합니다.
     long questionEndTime = System.currentTimeMillis();
     long timeTaken = questionEndTime - questionStartTime;

     // 각 사용자의 문제 풀이 소요 시간을 저장합니다.
     userQuestionTimes.add(new UserQuestionTime(userNames, currentQuestionIndex.get(), timeTaken));

    }// main

    // 사용자를 데이터베이스에 저장합니다.
    private static void saveUserToDB(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO USERS (USER_ID, USER_NAME) VALUES (USER_SEQ.NEXTVAL, ?)"; // SQL 쿼리를 작성합니다.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name); // 파라미터 값을 설정합니다.
            pstmt.executeUpdate(); // 쿼리를 실행하고 업데이트합니다.
        }
    }

    // 사용자의 점수를 업데이트하고 데이터베이스에 저장합니다.
    private static void updateScore(Connection conn, String userName, int score) throws SQLException {
        String sql = "MERGE INTO SCORES s " + "USING (SELECT USER_ID FROM USERS WHERE USER_NAME = ?) t "
                + "ON (s.USER_ID = t.USER_ID) " + "WHEN MATCHED THEN " + "UPDATE SET s.SCORE = ? "
                + "WHEN NOT MATCHED THEN " + "INSERT (USER_ID, SCORE) VALUES (t.USER_ID, ?)"; // SQL 쿼리를 작성합니다.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName); // 파라미터 값을 설정합니다.
            pstmt.setInt(2, score); // 파라미터 값을 설정합니다.
            pstmt.setInt(3, score); // 파라미터 값을 설정합니다.
            pstmt.executeUpdate(); // 쿼리를 실행하고 업데이트합니다.
        }
    }
}// class


