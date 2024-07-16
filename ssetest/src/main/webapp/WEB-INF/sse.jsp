<%@page import="java.util.Date"%>
<%
	response.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/event-scream");
	response.setHeader("Cache-Control", "no-cache");
	out.print("retry: 5\n\n");
	out.print("data: {"+new Date(System.currentTimeMillis())+"}\n\n");
	out.flush();
%>