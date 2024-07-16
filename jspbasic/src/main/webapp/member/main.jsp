<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>메인 페이지</h2>
	<h3>
		<%=session.getAttribute("uname")%>님
		(<%=session.getAttribute("uname")%>)
		<%=session.getAttribute("ucount")%>번 방문을 하셨습니다.		
	</h3>
	<p>
		<button onclick ="location.href='logout.jsp';">로그 아웃</button>
	</p>
</body>
</html>