<%
	StringBuilder sb= new StringBuilder();
	sb.append("[");
	sb.append("{\"name\":\"jjh1\", \"age\":30},");
	sb.append("{\"name\":\"jjh2\", \"age\":50}");
	sb.append("]");
	String str = sb.toString();
	out.print(str);
%>