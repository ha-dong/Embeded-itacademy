package teampl.chatQuiz;

import java.util.List;

class UserQuestionTime {//퀴즈 소요 시간 나오게 하기
    private String userName;
    private int questionIndex;
    private long timeTakenMillis;

    public UserQuestionTime(String userName, int questionIndex, long timeTakenMillis) {
        this.userName = userName;
        this.questionIndex = questionIndex;
        this.timeTakenMillis = timeTakenMillis;
    }

    public UserQuestionTime(List<String> usernames, int questionIndex2, long timeTaken) {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
        return userName;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public long getTimeTakenMillis() {
        return timeTakenMillis;
    }
}
