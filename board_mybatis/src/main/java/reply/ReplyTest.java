package reply;

import java.util.List;

public class ReplyTest {
	public static void main(String[] args) throws Exception {
		ReplyInterface ri = new ReplyDao();
		
		//insert
		Reply reply = new Reply(0, "댓글 작성자1", "댓글 내용1", null, 41); //null뒤에 숫자는 dbeaver에서 조회하여 id를 넣는 것
		ri.registReply(reply);
		
		reply = new Reply(0, "댓글 작성자1", "댓글 내용1", null, 41);
		ri.registReply(reply);
		
		reply = new Reply(0, "댓글 작성자1", "댓글 내용1", null, 41);
		ri.registReply(reply);

		//delete
//		int result = ri.deleteReply(3);
		
		//list
//		List<Reply> replyList = ri.listReply();
//		for(Reply eachReply:replyList) {
//			System.out.println(eachReply);
//		}
		
		
		
		
	}//main
}//class
