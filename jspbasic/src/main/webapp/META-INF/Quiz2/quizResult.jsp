<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	int count = 0;

	if(((String)request.getAttribute("quiz1")).equals("실미도"))count++;
	if(((String)request.getAttribute("quiz2")).equals("강남"))count++;
	if(((String)request.getAttribute("quiz3")).equals("명동"))count++;
	if(((String)request.getAttribute("quiz4")).equals("치킨"))count++;
	if(((String)request.getParameter("quiz5")).equals("조선 팰리스"))count++;

	out.print("<h2>" + count + "/5 문제 정답"+"</h2>");
	
	out.print("<button onclick=\"location.href='quiz1.jsp;'\">다시하기</button>");
%>