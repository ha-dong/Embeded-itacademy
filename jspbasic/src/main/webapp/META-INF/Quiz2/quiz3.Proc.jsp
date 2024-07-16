<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%	
	application.setAttribute("quiz3", request.getParameter("quiz4"));
	request.getRequestDispatcher("quiz4.jsp").forward(request, response);
%>