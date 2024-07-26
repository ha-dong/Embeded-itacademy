<%@page import="reply.Reply"%>
<%@page import="reply.ReplyDao"%>
<%@page import="reply.ReplyInterface"%>
<%@page import="board.BoardDao"%>
<%@page import="board.BoardInterface"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    String rwriter = request.getParameter("rwriter");
    String rcontent = request.getParameter("rcontent");
    int bid = Integer.parseInt(request.getParameter("bid"));

    ReplyInterface ri = new ReplyDao();
    BoardInterface bi = new BoardDao();

    Reply reply = new Reply(0, rwriter, rcontent, null, bid);
    ri.registReply(reply);
    bi.updateReplyCount(bid); // 게시물의 댓글 개수 증가
%>
