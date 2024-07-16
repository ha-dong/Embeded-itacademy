Hello_jsp.Hello
<%
	out.print("<h2>Hello JSP~</h2>");
	int x = 100;
	int y = 110;
	out.println(x+y);
	System.out.print(x+y);
%>
<br />
<%="hello" %>