package blog;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AuthorMain {
	public static void main(String[] args) {
		String resource = "conf/configuration.xml";
		Reader reader;
		SqlSession session = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			session = ssf.openSession();
			//1. author 테이블에 5개 행 입력
//			for(int i=1; i < 6; i++) { 
//				Author author = new Author(0, "저자"+i, 3);
//				session.insert("insertAuthor", author);
//				session.commit();
//			}
			
			//2. authorlist 출력
//			int listCount = session.selectOne("countAuthor");
//			System.out.println("Author 행의 수: "+listCount);
			
			//3. author 5번째 행 수정
//			Author author = new Author(5, "저자"+1 ,5);
//			session.update("updateAuthor", author);
//			session.commit();
			
			//4. author5번째 행 삭제
//			session.delete("deleteAuthor", 5);
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
