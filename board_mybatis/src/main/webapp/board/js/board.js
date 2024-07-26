$(function() {
});

let loginMid;

// getReplyList 함수 정의: 게시물 ID(bid)와 로그인한 사용자 ID(mid)를 인자로 받아 댓글 목록을 가져오는 함수
const getReplyList = function(bid, mid) {
   
    loginMid = mid; // 로그인한 사용자 ID를 글로벌 변수 loginMid에 저장
    
   
    $.get("/board_mybatis/board/api/replyList.jsp?bid=" + bid, function(jsonStr) { // GET 요청을 사용하여 댓글 목록을 가져오기 위해 서버에 요청을 보냄
        
        const replyArr = JSON.parse(jsonStr);// 서버에서 반환된 JSON 문자열을 JavaScript 객체 배열로 파싱       
        for (reply of replyArr) {// 댓글 배열을 순회하면서 각 댓글을 처리 
            let formattedDate = moment(reply.rregdate).format("HH:MM");// 댓글 등록 날짜를 'HH:MM' 형식으로 포맷 
            // 각 댓글을 HTML 문자열로 구성
            let eachReply = 
                "<p>["
                + formattedDate 
                + "] <b>" + reply.rwriter 
                + "</b> "
                + reply.rcontent;
            // 댓글 작성자가 현재 로그인한 사용자일 경우, 댓글에 삭제 링크를 추가
            if (mid == reply.rwriter) {
                eachReply += " <a href='javascript:deleteReply(\"" + reply.bid + "\", \"" 
                + reply.rid + "\");'>[X]</a>";
            } else {
                // 댓글 작성자가 현재 로그인한 사용자가 아닐 경우, 추가적인 HTML은 없음
                eachReply += "";                    
            }   
            eachReply += "</p>"; // HTML 문자열을 닫는 </p> 태그 추가
            $("#replyList").append(eachReply);// #replyList 요소에 댓글 HTML을 추가
        }
    });
}

// 댓글을 등록하는 함수 정의
const registReply = function(rwriter, bid) {
    // POST 요청을 사용하여 서버에 댓글 등록 요청을 보냄
    $.post(
        "/board_mybatis/board/api/registReply.jsp", // 요청을 보낼 URL
        {
            "rwriter": rwriter, // 댓글 작성자
            "rcontent": $("#rcontent").val(), // 댓글 내용 (입력 필드에서 가져옴)
            "bid": bid // 댓글이 달릴 게시물 ID
        }
    ).done(function() {
        // 댓글 등록이 완료된 후 실행되는 콜백 함수
        $("#replyList").html(""); // 댓글 목록을 비움
        getReplyList(bid, loginMid); // 댓글 목록을 다시 가져와서 업데이트
    });
};

// 댓글을 삭제하는 함수 정의
const deleteReply = function(bid, rid) {
    // GET 요청을 사용하여 서버에 댓글 삭제 요청을 보냄
    $.get("/board_mybatis/board/api/deleteReply.jsp?bid=" + bid + "&rid=" + rid, function() {
        // 서버 응답이 완료된 후 실행되는 콜백 함수 (비어 있음)
    }).done(function() {
        // 댓글 삭제가 완료된 후 실행되는 콜백 함수
        $("#replyList").html(""); // 댓글 목록을 비움
        getReplyList(bid, loginMid); // 댓글 목록을 다시 가져와서 업데이트
    });
};