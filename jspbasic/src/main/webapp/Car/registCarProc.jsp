<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp: useBean id= "car" class="jspbasic.Car" />
<jsp: setProperty name= "car" property="*" />
 	
 	<%
 		Connection conn = DriverManager
 				.getConnection("jsbc:oracle:thin:@127.0.0.1:1521:XE", "JJH", "1234");
 	
 		String sql = "insert into Car values(seq_car.nextval,?,?,?,?,?)";
 		
 		PreparedStatement pstmt = conn.prepareStatement(sql);
 		pstmt.setString(1, car.getMake());
 		pstmt.setString(2, car.getModelName());
 		pstmt.setInt(3, car.getCc());
 		pstmt.setString(4, car.getColor());
 		pstmt.setInt(5, car.getPrice());
 		
 		int result = pstmt.executeUpdate();
 		if(result > 0){
 			out.print("등록성공");
 		}else{
 			out.print("등록 실패");
 		}
 		pstmt.close();
    %>