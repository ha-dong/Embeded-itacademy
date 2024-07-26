package reply;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.Board;

public class ReplyDao implements ReplyInterface {
	
	private static Reader reader = null;
	private static SqlSessionFactory ssf = null;
	
	static {
		try {
			reader = Resources.getResourceAsReader("conf/configuration.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

	@Override
	public List<Reply> listReply(int bid) throws Exception {
	    // MyBatis의 SqlSession을 열고, 'reply.listReply' 쿼리를 실행하여 댓글 목록을 가져옵니다.
	    // bid는 댓글을 가져올 게시물의 ID입니다.
	    return ssf.openSession().selectList("reply.listReply", bid);
	}


	@Override
	public int registReply(Reply reply) throws Exception {
	    // 자동 커밋 모드로 SqlSession을 열어줍니다.
	    SqlSession ss = ssf.openSession(true);
	    
	    // 댓글을 등록할 게시물의 댓글 수를 증가시키기 위한 Board 객체를 생성합니다.
	    Board board = new Board();
	    board.setBid(reply.getBid()); // 댓글이 달릴 게시물의 ID를 설정합니다.
	    board.setRcount(1); // 댓글 수를 1로 설정합니다 (댓글 추가로 인해 댓글 수가 1 증가함을 의미).
	    
	    // 'board.changeRcount' 쿼리를 실행하여 게시물의 댓글 수를 업데이트합니다.
	    ss.update("board.changeRcount", board);
	            
	    // 댓글을 데이터베이스에 등록합니다.
	    int result = ss.insert("reply.registReply", reply);
	    
	    // SqlSession을 닫아줍니다.
	    ss.close();
	    
	    // 등록된 댓글의 결과를 반환합니다.
	    return result;
	}

	@Override
	public int deleteReply(Reply reply) throws Exception {
		SqlSession ss = ssf.openSession(true);
		
		Board board = new Board();
		board.setBid(reply.getBid());
		board.setRcount(-1);
		ss.update("board.changeRcount", board);		
		
		int result = ss.delete("reply.deleteReply", reply.getRid());
		ss.close();
		return  result;		
	}

}