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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatQuiz {

    // 서버가 사용할 포트 번호
    private static final int PORT = 9999;

    // 문제의 수
    private static final int NUM_QUESTIONS = 5;

    // 클라이언트 소켓을 저장할 리스트
    private static final List<Socket> clientSockets = new ArrayList<>();

    // 프로그램의 시작점
    public static void main(String[] args) {
    	
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
        	
            // 서버 소켓 생성 및 포트 번호 출력
            System.out.println("문제 풀이 방 번호 : " + PORT);

            // 클라이언트 연결 수락 및 소켓 리스트에 추가
            for (int i = 0; i < NUM_QUESTIONS; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("플레이어의 접속을 확인했습니다");
                clientSockets.add(clientSocket);
            }

            // 문제와 정답을 읽어서 클라이언트에게 전송
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("questions.properties", StandardCharsets.UTF_8))) {
                Properties props = new Properties();
                props.load(reader);
                for (int i = 1; i <= NUM_QUESTIONS; i++) {
                    String questionKey = "question" + i;
                    String answerKey = "answer" + i;
                    String question = props.getProperty(questionKey);
                    String answer = props.getProperty(answerKey);
                    
                    for (Socket clientSocket : clientSockets) {
                    	
                        // 클라이언트에게 문제 전송
                        PrintWriter out = new PrintWriter(
                                new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
                        out.println(question);
                        // 클라이언트로부터 답변을 받아서 정답과 비교 후 결과 전송
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                        String userAnswer = in.readLine();
                        if (userAnswer != null && userAnswer.equalsIgnoreCase(answer.trim())) {
                            out.println("정답입니다!");
                        } else {
                            out.println("틀렸습니다. 정답은 " + answer + "입니다.");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}