<%@ page contentType="text/html; charset=utf-8" %>

<%
	pageContext.setAttribute("pv","page");
	request.setAttribute("rv", "request");
	session.setAttribute("sv", "request");
	application.setAttribute("av", "request");
	
	out.print(pageContext.getAttribute("pv")+"<br />");
	out.print(request.getAttribute("rv")+"<br />");
	out.print(session.getAttribute("sv")+"<br />");
	out.print(session.getId()+"<br />");
	out.print(application.getAttribute("av")+"<br />");
%>

<a href="attributeVariable2.jsp">page2</a>