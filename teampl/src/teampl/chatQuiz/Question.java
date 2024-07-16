package teampl.chatQuiz;

import java.awt.Font;

public class Question  {

	private final String question;
	private final String answer;

	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public boolean isCorrect(String response) {
		return answer.equalsIgnoreCase(response.trim());
	}

	public String getAnswer() {
		// TODO Auto-generated method stub
		return null;
	}
}
