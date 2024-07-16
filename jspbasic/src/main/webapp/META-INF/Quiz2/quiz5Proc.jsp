<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

	request.setAttribute("quiz5", request.getParameter("quiz5"));
	request.getRequestDispatcher("quizResult.jsp").forward(request, response);
%>