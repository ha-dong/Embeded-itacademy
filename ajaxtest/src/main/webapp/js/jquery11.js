$(function(){
    //jquery ajax실습
    //http://jsonplaceholder.typicode.com/todos//테이블에 출력
    $("#btn").click(function(){
		$.ajax({
			method: "GET",
			url: "http://jsonplaceholder.typicode.com/todos"
		})
		.done(function(jsonArr, status){//변수지정
			if(status=="success"){
				const jsonArrLeng = jsonArr.length;
				for(let i=0; i<jsonArrLeng; i++){
					const tr = $("<tr></tr>");
					tr.append("<td>"+jsonArr[i].userId+"</td>");
					tr.append("<td>"+jsonArr[i].id+"</td>");
					tr.append("<td>"+jsonArr[i].title+"</td>");
					tr.append("<td>"+(jsonArr[i].completed==true?"완료":"미완료")+"</td>");
					$("tbody").append(tr);//받을 공간
				}
			}
		});
	});//btn function
});//맨 위에 $function