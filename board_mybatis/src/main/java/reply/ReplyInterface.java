package reply;

import java.util.List;

public interface ReplyInterface {
	
	public abstract List<Reply> listReply(int bid) throws Exception;
	public abstract int registReply(Reply reply)throws Exception;//댓글 등록
	public abstract int deleteReply(Reply reply)throws Exception;//댓글 삭제
	
	
}
