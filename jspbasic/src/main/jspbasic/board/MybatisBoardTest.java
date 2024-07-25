package jspbasic.board;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisBoardTest {
    public static void main(String[] args) {
        String resource = "conf/configuration.xml";
        Reader reader = null;
        SqlSession session = null;

        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();

            // insert
	            // Board board = new Board(0, "sample title", "sample content", "Sample Author", 0, new Timestamp(System.currentTimeMillis()), "sort", "cfn", "sfn");
	            // int result = session.insert("jspbasic.board.UserMapper.insertBoard", board);
	            // session.commit();
	            // if(result > 0) {
	            //     System.out.println("입력 성공");
	            // } else {
	            //     System.out.println("입력 실패");
	            // }

            // select
	            // List<Board> boardList = session.selectList("jspbasic.board.UserMapper.selectAllBoards");
	            // for(Board board : boardList) {
	            //     System.out.println(board);
	            // }

            // delete
	            // int result = session.delete("jspbasic.board.UserMapper.deleteBoard", 3);
	            // session.commit();
	            // if(result > 0) {
	            //     System.out.println("삭제 성공");
	            // } else {
	            //     System.out.println("삭제 실패");
	            // }

            // update
	            // Board board = new Board(1, "updated title", "updated content", "Updated Author", 100, new Timestamp(System.currentTimeMillis()), "updatedSort", "updatedCfn", "updatedSfn");
	            // int result = session.update("jspbasic.board.UserMapper.updateBoard", board);
	            // session.commit();
	            // if(result > 0) {
	            //     System.out.println("수정 성공");
	            // } else {
	            //     System.out.println("수정 실패");
	            // }

            // selectOne
	            // Board board = session.selectOne("jspbasic.board.UserMapper.selectBoard", 1);
	            // System.out.println(board);
            
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        } finally {
	            if (session != null) session.close();
	        }
    }
}
