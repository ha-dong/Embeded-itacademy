<%
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	out.print("{\"name\":\""+name+"\",\"age\""+age+"\"}");
%>