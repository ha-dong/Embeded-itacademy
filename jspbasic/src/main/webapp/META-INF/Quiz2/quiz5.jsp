<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <h2>'호텔스 매거진'이 선정한 전 세계 25개 도시의 최고 호텔인 '25x25 Top Hotels of the World'에 올라간 우리나라 호텔은?</h2>
  <form action "quizResult.jsp">
    <input type="hidden" name="quiz1" value='<%=request.getAttribute("quiz1")%>'/>
    <input type="hidden" name="quiz2" value='<%=request.getAttribute("quiz2")%>'/>
    <input type="hidden" name="quiz3" value='<%=request.getAttribute("quiz3")%>'/>
    <input type="hidden" name="quiz4" value='<%=request.getAttribute("quiz4")%>'/>
  	정답:<input type = "text" name="quiz5"/>&nbsp;
  		<input type ="submit" value="다음 문제">
  </form>
  