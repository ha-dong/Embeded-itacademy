<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	request.setAttribute("quiz4", request.getParameter("quiz5"));
	request.getRequestDispatcher("quiz5.jsp").forward(request, response);
%>