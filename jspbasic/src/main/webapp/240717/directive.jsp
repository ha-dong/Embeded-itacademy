<%@ page language="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>

<%@include file="top.jsp"%>

<h2>directive.jsp</h2>

<%
	//top.jsp에 선언한 변수 선언 불가
	//Strgin message = "directive"
%>


<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:out value="hello core tag" />