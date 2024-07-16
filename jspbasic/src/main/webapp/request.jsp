<%@ page contentType="text/html; charset=utf-8" pageEncoding = "utf-8" %>
<%
	//post방식으로 전송된 데이터 인코딩
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	out.print("이름: "+ name + "<br />");
	out.print("나이: "+ age + "<br />");
	
	System.out.println(request.getMethod());//http메소드
	System.out.println(request.getCharacterEncoding());//캐릭터인코딩 방식
	System.out.println(request.getContentLength());//전송한 데이터 길이
	System.out.println(request.getContentType());//MIME타입
	System.out.println(request.getContextPath());//컨텍스트 루트 = 웹 어플리케이션 루트
	System.out.println(request.getLocalAddr());//로컬 주소
	System.out.println(request.getLocalPort());//톰캣 버전 번호
	System.out.println(request.getProtocol());// 프로토콜 명/버전
	System.out.println(request.getQueryString());//GET으로 URL뒤에 붙인 데이터
	System.out.println(request.getRemoteAddr());//원격 주소
	System.out.println(request.getRemoteHost());//원격 호스트명
%>