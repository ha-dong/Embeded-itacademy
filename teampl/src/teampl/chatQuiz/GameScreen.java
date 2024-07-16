package teampl.chatQuiz;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameScreen extends JFrame {

    private JLabel lblQuestion;
    private JButton btnSubmit;
    private JTextField txtAnswer;

    private List<Question> questions;
    private int currentQuestionIndex;

    public GameScreen() {
        this.questions = loadQuestions();
        this.currentQuestionIndex = 0;
        initialize();
        displayCurrentQuestion();
    }

    private void initialize() {
        setTitle("퀴즈 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        lblQuestion = new JLabel(""); // 빈 레이블 생성
        lblQuestion.setFont(new Font("Gulim", Font.PLAIN, 14));
        lblQuestion.setBounds(20, 20, 400, 50);
        getContentPane().add(lblQuestion);

        txtAnswer = new JTextField();
        txtAnswer.setBounds(20, 80, 400, 30);
        getContentPane().add(txtAnswer);

        btnSubmit = new JButton("답 제출");
        btnSubmit.addActionListener(e -> submitAnswer());
        btnSubmit.setBounds(180, 130, 90, 30);
        getContentPane().add(btnSubmit);
    }

    private void displayCurrentQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            lblQuestion.setText(currentQuestion.getQuestion()); // 질문 텍스트 설정
            txtAnswer.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "퀴즈 종료!");
        }
    }

    private void submitAnswer() {
        String answer = txtAnswer.getText().trim();
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion != null) {
            if (currentQuestion.isCorrect(answer)) {
                JOptionPane.showMessageDialog(this, "정답입니다!");
            } else {
                JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + currentQuestion.getAnswer() + "입니다.");
            }
            currentQuestionIndex++;
            displayCurrentQuestion();
        } else {
            // currentQuestion이 null인 경우에 대한 예외 처리 또는 적절한 조치를 취합니다.
        }
    }

    private List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("국보 1호였던 문화재의 이름은?", "숭례문"));
        questions.add(new Question("불로장생을 꿈꿔 전 세계를 뒤져 불로초를 찾았던 왕의 이름은?", "진시황"));
        questions.add(new Question("덧셈, 뺄셈, 곱셈, 나눗셈의 네 종류의 계산법을 뭐라고 하는가?", "사칙연산"));
        // 다른 문제들 추가
        return questions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameScreen gameScreen = new GameScreen();
            gameScreen.setVisible(true);
        });
    }
}