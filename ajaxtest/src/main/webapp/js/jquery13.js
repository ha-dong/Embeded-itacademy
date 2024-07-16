$(function(){
    //jquery ajax실습
    //http://jsonplaceholder.typicode.com/comments?postId=1
    $("#btn").click(function(){
		$("tbody").empty();
		const postIdVal = $("postID").val();
		$.ajax({
			method: "GET",
			url: "http://jsonplacehsolder.typicode.com/comments?postId="+postIdVal})
		.done(function(jsonArr, result){
			if(result=="success"){
				const jsonArrLeng = jsonArr.length;
				for(let i=0; i<jsonArrLeng; i++){
					const tr1 = $("<tr></tr>");
					tr1.append("<td>"+jsonArr[i].postId + "</td>");
					tr1.append("<td>"+jsonArr[i].id + "</td>");
					tr1.append("<td>"+jsonArr[i].name + "</td>");
					tr1.append("<td>"+jsonArr[i].email + "</td>");
					const tr2 = $("<tr></tr>");
					tr2.append("<td colspan='4'>"+jsonArr[i].body+"</td");
						$("tbody").append(tr1);
						$("tbody").append(tr2);
				}//for
			}//if
		})//done
	});
});//맨 위에 $function