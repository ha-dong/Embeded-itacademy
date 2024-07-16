<%@ page contentType="text/html; charset=utf-8" pageEncoding = "utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	Integer num1 = Integer.parseInt(request.getParameter("number1"));
	Integer num2 = Integer.parseInt(request.getParameter("number2"));
	String result = request.getParameter("result");
	
	int gyulgwa = 0;
	String Str = "";
	if(result.equals("+")){
		gyulgwa = num1 + num2;
		Str = "합은";
	}else if(result.equals("-")){
		gyulgwa = num1 - num2;
		Str = "차는";
	}else if(result.equals("*")){
		gyulgwa = num1 * num2;
		Str = "곱은";
	}else if(result.equals("/")){
		gyulgwa = num1 / num2;
		Str = "몫은";
	}

	else{
	out.print("두 수의  "+ Str + "" + result + "입니다");
	}
%>