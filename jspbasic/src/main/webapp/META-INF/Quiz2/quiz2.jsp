<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <h2>수도권 전철 하루 이용객이 많은 역은?</h2>
  <form action = Quiz2Proc.jsp>
  <input type="hidden" name="quiz1" value='<%=request.getAttribute("quiz1")%>'/>	
  	정답:<input type = "text" name="quiz2"/>&nbsp;
  		<input type ="submit" value="다음 문제">
  </form>