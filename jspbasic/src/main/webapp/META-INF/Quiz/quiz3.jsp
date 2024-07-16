<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <h2>외국 관강객이 가장 많이 찾는 장소 1위</h2>
  <form action = Quiz3Proc.jsp>
  	<input type="hidden" name="quiz1" value='<%=request.getAttribute("quiz1")%>'/>
    <input type="hidden" name="quiz2" value='<%=request.getAttribute("quiz2")%>'/>
   
  	정답:<input type = "text" name="quiz3"/>&nbsp;
  		<input type ="submit" value="다음 문제">
  </form>
  