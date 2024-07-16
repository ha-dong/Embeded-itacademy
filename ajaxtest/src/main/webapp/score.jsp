<%
    request.setCharacterEncoding("utf-8");
    
    String s1name = request.getParameter("s1name");
    int s1kor = Integer.parseInt(request.getParameter("s1kor"));
    int s1eng = Integer.parseInt(request.getParameter("s1eng"));
    int s1math = Integer.parseInt(request.getParameter("s1math"));
    int s1total = s1kor + s1eng + s1math;

    String s2name = request.getParameter("s2name");
    int s2kor = Integer.parseInt(request.getParameter("s2kor"));
    int s2eng = Integer.parseInt(request.getParameter("s2eng"));
    int s2math = Integer.parseInt(request.getParameter("s2math"));
    int s2total = s2kor + s2eng + s2math;

    String s3name = request.getParameter("s3name");
    int s3kor = Integer.parseInt(request.getParameter("s3kor"));
    int s3eng = Integer.parseInt(request.getParameter("s3eng"));
    int s3math = Integer.parseInt(request.getParameter("s3math"));
    int s3total = s3kor + s3eng + s3math;

	 response.setContentType("application/json; charset=utf-8");

    out.print("[");
    out.print("{\"name\":\"" + s1name + "\", \"kor\":" + s1kor + ", \"eng\":" + s1eng + ", \"math\":" + s1math + ", \"total\":" + s1total + "},");  
    out.print("{\"name\":\"" + s2name + "\", \"kor\":" + s2kor + ", \"eng\":" + s2eng + ", \"math\":" + s2math + ", \"total\":" + s2total + "},");  
    out.print("{\"name\":\"" + s3name + "\", \"kor\":" + s3kor + ", \"eng\":" + s3eng + ", \"math\":" + s3math + ", \"total\":" + s3total + "}");  
    out.print("]");
%>