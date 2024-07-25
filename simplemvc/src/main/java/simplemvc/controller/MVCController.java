package simplemvc.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplemvc.command.AgeCommand;
import simplemvc.command.Command;
import simplemvc.command.HobbyCommand;
import simplemvc.command.NameCommand;

// 컨트롤러의 역할
// 요청을 받아서 요청을 분석하고 요청파라미터에 따라 포워딩할 뷰와 뷰에 표시할 데이터(모델)를 결정하고
// 뷰로 포워딩

	@WebServlet(urlPatterns = "/controller")
	public class MVCController extends HttpServlet {
			
		   @Override
		   protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		      processRequest(req, resp);
		   }
	
		   @Override
		   protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		      processRequest(req, resp);
		   }
	
		   private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
			   //command.properties파일을 읽어서 Properties맵에 저장
			   Properties prop = new Properties();
			   File file = new File("E:\\embeded\\eclipse_workspace\\simplemvc\\src\\main\\java\\simplemvc\\controller\\MVCController.java");
			   prop.load(new FileReader(file));
			   
			   //properties 파일에 명령 = 명령처리클래스를 매핑한다.
			   //커맨드 패턴
			   Command command = null;
			   try {
				   //명령에 따른 클래스를 생성
				   Class cl = Class.forName((String)(prop.get(req.getQueryString())));
				   //명령 처리 클래스의 객체를 생성
				   command = (Command)cl.newInstance();
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }
			   System.out.println(prop.get(req.getQueryString()));
			

      // 컨트롤러의 역할 1. 요청 파라미터
	      String paramRequest = req.getParameter("request")
	            == null ? "" : req.getParameter("request");

      // 요청 처리 후 결과를 저장할 객체
      	Object resultObj = null;

      // 처리 결과를 표현할 View 로 사용할 JSP의 경로
      	Object viewJSP = null;

      // 컨트롤러의 역할 2. 요청에 따라 결과(Model)를 생성하고 뷰(View)를 결정
//	      if (paramRequest.equals("name")) {
//	         resultObj = "홍길동";
//	         viewJSP = "/jsp/name.jsp";
//	         
//	      } else if (paramRequest.equals("age")) {
//	         resultObj = 30;
//	         viewJSP = "/jsp/age.jsp";
//	         
//	      }else if (paramRequest.equals("hobby")) {
//	          resultObj = "축구";
//	          viewJSP = "/jsp/hobby.jsp";
//	      }
      	
      	//command 패턴 이용
      	if(paramRequest.equals("name")) {
      		command = new NameCommand();
      	} else if (paramRequest.equals("age")) {
      		command = new AgeCommand();
	     }else if (paramRequest.equals("hobby")) {
	    	command = new HobbyCommand();
	     }else if (paramRequest.equals("gender")) {
		    	command = new gendeCommand();
		     }
      
      // request 에 속성변수 설정 (이걸 jsp 에 전달) 
          req.setAttribute("resultObj", resultObj);
      
      // 컨트롤러의 역할 3. 생성된 결과를 뷰에 전달
	      RequestDispatcher dispacher = req.getRequestDispatcher((String)viewJSP);
	      dispacher.forward(req, resp);
	      
		   }//processRequest
	}//class
