package blog;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CommentMain {
	public static void main(String[] args) {
		String resource = "conf/configuration.xml";
		Reader reader;
		SqlSession session = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			session = ssf.openSession();
			//1. comment테이블에 5개 행 입력
			for(int i=1; i < 6; i++) { 
				Comment comment = new Comment(0,"comment제목"+i,"comment내용"+i, 0);
				session.insert("insertBlog", comment);
				session.commit();
			}
			
			//2. bloglist 출력
//			int listCount = session.selectOne("countComment");
//			System.out.println("comment 행의 수: "+listCount);
			
			//3. blog 5번째 행 수정
//			Comment comment = new Comment(5, "제목수정", "내용수정", null, null, 0);
//			session.update("updateComment", comment);
//			session.commit();
			
			//4. blog 5번째 행 삭제
//			session.delete("deleteComment", 5);
//			session.commit();
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		
	}//main
}//class
