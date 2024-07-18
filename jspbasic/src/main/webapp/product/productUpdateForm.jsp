<%@page import="jspbasic.product.ProductProc"%>
<%@page import="jspbasic.product.Product"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
	ProductProc productProc = new ProductProc();
	Product product = productProc.getProduct(Integer.parseInt(request.getParameter("pno")));
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>product</title>
</head>
<body>
<div id="wrapper">
	<input type="hidden" name="pmdate" value="<%=product.getPmdate()%>"/><br />
		회사명 : <input type="text" name="pcomp" value="<%=product.getPcomp()%>"/><br />
		상품명 : <input type="text" name="pname" value="<%=product.getPname()%>"/><br />
		색상 : <input type="text" name="pcolor" value="<%=product.getPcolor()%>"/><br />
		수량 : <input type="text" name="pamt" value="<%=product.getPamt()%>"/><br />
		가격 : <input type="text" name="pprice" value="<%=product.getPprice()%>"/><br />
		제조일시 : <input type="date" name="pmdate" value="<%=product.getPmdate()%>"/><br />
		<input type="submit" value="확인" />
	</form>
</div>
</body>
</html>