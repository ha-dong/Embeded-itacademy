<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:useBean id="car" class="jspbasic.Car" />
    <jsp:setProperty name="car" property="*" />
    
    <jsp:forward page ="registCarProc.jsp">
    	<jsp:param name ="yearNum" value="${car.yearNum}" />
    	<jsp:param name ="make;" value="${car.make}" />
    	<jsp:param name ="modelName;" value="${car.modelName}" />
    	<jsp:param name ="cc;" value="${car.cc}" />
    	<jsp:param name ="color;" value="${car.color}" />
    	<jsp:param name ="price;" value="${car.price}" />
    </jsp:forward>
    
    연번: ${car.yearNum}<br />
   	제조사: ${car.make}<br />
    모델명: ${car.modelName}<br />
    배기량: ${car.cc}<br />
    색상: ${car.color}<br />
    가격: ${car.price}<br />