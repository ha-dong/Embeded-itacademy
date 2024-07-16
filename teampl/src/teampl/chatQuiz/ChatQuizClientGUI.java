package teampl.chatQuiz;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ChatQuizClientGUI {

	private JFrame frame;
	private JTextArea textArea;
	private JTextField inputField;
	private JButton submitButton;
	private PrintWriter out;
	private BufferedReader in;
	private String userName;
	private boolean gameEnded = false;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ChatClient().startClient());
	}

	public void startClient() {
		initialize();

		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame("Chat Quiz Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600); // 크기 조정

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(scrollPane, BorderLayout.CENTER);

		inputField = new JTextField();

		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(inputField, BorderLayout.CENTER);

		submitButton = new JButton("제출");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		southPanel.add(submitButton, BorderLayout.EAST);

		frame.add(southPanel, BorderLayout.SOUTH);

		inputField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		JLabel lblNewLabel = new JLabel("퀴즈 채팅방에 오신걸 환영합니다!!");
		lblNewLabel.setFont(new Font("궁서체", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH); // 상단에 배치

		// 게임 시작 버튼 및 게임 설명 버튼 패널 생성
		JPanel buttonPanel = new JPanel(new BorderLayout());

		JButton btnStartGame = new JButton("게임 시작");
		btnStartGame.setPreferredSize(new Dimension(200, 50)); // 버튼 크기 조정
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 게임 시작 버튼 눌렀을 때 이름 입력 다이얼로그 보이기
				userName = JOptionPane.showInputDialog(frame, "당신의 이름을 입력해주세요!");
				if (userName != null && !userName.trim().isEmpty()) {
					new Thread(() -> runClient()).start();
				} else {
					JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
					System.exit(0);
				}
			}
		});
		buttonPanel.add(btnStartGame, BorderLayout.CENTER);

		JButton btnGameDescription = new JButton("게임 설명");
		btnGameDescription.setPreferredSize(new Dimension(200, 50)); // 버튼 크기 조정
		btnGameDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 게임 설명 다이얼로그 보이기
				JOptionPane.showMessageDialog(frame,
						"1. 게임시작버튼을 누르면 유저가 이름을 입력한다\n" + "2. 모든 유저가 이름을 입력하면 문제가 나오기 시작한다\n"
								+ "3. 모든 유저가 답을 입력하면 3초의 대기시간을 가진 뒤 다음 문제로 넘어간다\n"
								+ "*모든 유저가 답을 입력하지 않으면 다음 문제로 안넘어간다");
			}
		});
		buttonPanel.add(btnGameDescription, BorderLayout.SOUTH);

		frame.getContentPane().add(buttonPanel, BorderLayout.EAST); // 오른쪽 가운데에 배치
	}

	private void runClient() {
		try {
			Socket socket = new Socket("192.168.8.14", 9999);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			out.println(userName);

			String message;
			while ((message = in.readLine()) != null) {
				handleServerMessage(message);
			}

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		if (!gameEnded) {
			String message = inputField.getText().trim();
			if (!message.isEmpty()) {
				out.println(message);
				inputField.setText("");
			}
		}
	}

	private void handleServerMessage(String message) {
		if (!gameEnded) {
			if (message.startsWith("플레이어")) {
				appendMessage(message);
			} else {
				appendMessage(message);
				scrollToBottom();
			}
		}
	}

	private void appendMessage(String message) {
		textArea.append(message + "\n");
	}

	private void scrollToBottom() {
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
