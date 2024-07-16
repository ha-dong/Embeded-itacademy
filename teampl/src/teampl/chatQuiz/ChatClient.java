package teampl.chatQuiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ChatClient{

    // GUI 컴포넌트들과 네트워크 스트림, 상태 변수들을 정의
    private JFrame frame; // 메인 프레임
    private JTextArea textArea; // 서버 메시지 출력용 텍스트 영역
    private JTextField inputField; // 사용자 입력 필드
    private JButton submitButton; // 제출 버튼
    private PrintWriter out; // 서버로의 출력 스트림
    private BufferedReader in; // 서버로부터의 입력 스트림
    private String userName; // 사용자 이름
    private boolean gameEnded = false; // 게임 종료 상태 플래그

    // 메인 메서드, 프로그램 시작 지점
    public static void main(String[] args) {
        // 스윙 이벤트 디스패치 스레드에서 클라이언트를 시작
        SwingUtilities.invokeLater(() -> new ChatClient().startClient());
    }

    // 클라이언트 시작 메서드
    public void startClient() {
        initialize(); // GUI 초기화
        frame.setVisible(true); // 프레임을 보이게 함
    }

    // GUI 초기화 메서드
    private void initialize() {
        // 메인 프레임 설정
        frame = new JFrame("Chat Quiz Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // 프레임 크기 설정

        // 텍스트 영역 설정
        textArea = new JTextArea();
        textArea.setEditable(false); // 사용자가 텍스트 영역을 수정하지 못하도록 설정
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 스크롤 가능한 패널에 텍스트 영역을 추가
        frame.add(scrollPane, BorderLayout.CENTER); // 텍스트 영역을 중앙에 배치

        // 사용자 입력 필드 설정
        inputField = new JTextField();

        // 하단 패널 설정 및 추가
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(inputField, BorderLayout.CENTER); // 입력 필드를 하단 패널의 중앙에 추가

        // 제출 버튼 설정
        submitButton = new JButton("제출");
        submitButton.addActionListener(new ActionListener() { // 버튼 클릭 시 동작 설정
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(); // 메시지 전송
            }
        });
        southPanel.add(submitButton, BorderLayout.EAST); // 버튼을 하단 패널의 오른쪽에 추가

        frame.add(southPanel, BorderLayout.SOUTH); // 하단 패널을 프레임의 남쪽에 추가

        // 입력 필드에 액션 리스너 추가 (엔터키 입력 시 메시지 전송)
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(); // 메시지 전송
            }
        });

        // 상단 라벨 설정 및 추가
        JLabel lblNewLabel = new JLabel("퀴즈 채팅방에 오신걸 환영합니다!!");
        lblNewLabel.setFont(new Font("궁서체", Font.PLAIN, 25)); // 폰트 설정
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
        frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH); // 라벨을 프레임의 북쪽에 추가

        // 오른쪽 버튼 패널 설정 및 추가
        JPanel buttonPanel = new JPanel(new BorderLayout());

        // 게임 시작 버튼 설정
        JButton btnStartGame = new JButton("게임 시작");
        btnStartGame.setPreferredSize(new Dimension(200, 50)); // 버튼 크기 설정
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 게임 시작 버튼 클릭 시 동작 설정
                userName = JOptionPane.showInputDialog(frame, "당신의 이름을 입력해주세요!"); // 이름 입력 다이얼로그 표시
                if (userName != null && !userName.trim().isEmpty()) {
                    new Thread(() -> runClient()).start(); // 이름이 유효하면 클라이언트 실행
                } else {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty."); // 이름이 비어있으면 경고 메시지 표시
                    System.exit(0); // 프로그램 종료
                }
            }
        });
        buttonPanel.add(btnStartGame, BorderLayout.CENTER); // 버튼을 패널의 중앙에 추가

        // 게임 설명 버튼 설정
        JButton btnGameDescription = new JButton("게임 설명");
        btnGameDescription.setPreferredSize(new Dimension(200, 50)); // 버튼 크기 설정
        btnGameDescription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 게임 설명 버튼 클릭 시 동작 설정
                JOptionPane.showMessageDialog(frame,
                        "1. 게임시작버튼을 누르면 유저가 이름을 입력한다\n" +
                        "2. 모든 유저가 이름을 입력하면 문제가 나오기 시작한다\n" +
                        "3. 모든 유저가 답을 입력하면 3초의 대기시간을 가진 뒤 다음 문제로 넘어간다\n" +
                        "*모든 유저가 답을 입력하지 않으면 다음 문제로 안넘어간다"); // 게임 설명 다이얼로그 표시
            }
        });
        buttonPanel.add(btnGameDescription, BorderLayout.SOUTH); // 버튼을 패널의 아래쪽에 추가

        frame.getContentPane().add(buttonPanel, BorderLayout.EAST); // 버튼 패널을 프레임의 동쪽에 추가
    }

    // 클라이언트 실행 메서드
    private void runClient() {
        try {
            // 서버와 연결
            Socket socket = new Socket("192.168.8.15", 9999); // 서버 IP와 포트로 소켓 생성
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),
            		StandardCharsets.UTF_8), true); // 출력 스트림 설정
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),
            		StandardCharsets.UTF_8)); // 입력 스트림 설정

            out.println(userName); // 서버에 사용자 이름 전송

            String message;
            while ((message = in.readLine()) != null) {
                handleServerMessage(message); // 서버로부터 메시지를 읽어 처리
            }

            socket.close(); // 소켓 닫기
        } catch (IOException e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
    }

    // 메시지 전송 메서드
    private void sendMessage() {
        if (!gameEnded) { // 게임이 종료되지 않은 경우
            String message = inputField.getText().trim(); // 입력 필드의 메시지를 가져옴
            if (!message.isEmpty()) {
                out.println(message); // 메시지를 서버로 전송
                inputField.setText(""); // 입력 필드 초기화
            }
        }
    }

    // 서버 메시지 처리 메서드
    private void handleServerMessage(String message) {
        if (!gameEnded) { // 게임이 종료되지 않은 경우
            if (message.startsWith("플레이어")) {
                appendMessage(message); // 플레이어 관련 메시지를 추가
            } else {
                appendMessage(message); // 기타 메시지를 추가
                scrollToBottom(); // 스크롤을 맨 아래로 이동
            }
        }
    }

    // 메시지를 텍스트 영역에 추가하는 메서드
    private void appendMessage(String message) {
        textArea.append(message + "\n"); // 메시지를 텍스트 영역에 추가
    }

    // 텍스트 영역을 맨 아래로 스크롤하는 메서드
    private void scrollToBottom() {
        textArea.setCaretPosition(textArea.getDocument().getLength()); // 텍스트 영역을 맨 아래로 스크롤
    }
}


