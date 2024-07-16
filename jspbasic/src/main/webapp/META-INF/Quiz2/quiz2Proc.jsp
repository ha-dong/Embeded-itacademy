<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	application.setAttribute("quiz1", request.getParameter("quiz2"));
	request.getRequestDispatcher("quiz3.jsp");
%>