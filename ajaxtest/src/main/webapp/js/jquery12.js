$(function(){
    //jquery ajax실습
    //http://jsonplaceholder.typicode.com/albums
   $("#btn").click(function(){
		$.ajax({
			method:"GET",
			url:"http://jsonplaceholder.typicode.com/albums"
		}).done(function(jsonArr){
			const jsonArrLeng = jsonArr.length;
			for(let i=0; i<jsonArrLeng; i++){
					const tr = $("<tr></tr>");
					tr.append("<td>"+jsonArr[i].userId+"</td>");
					tr.append("<td>"+jsonArr[i].id+"</td>");
					tr.append("<td>"+jsonArr[i].title+"</td>");
					$("tbody").append(tr);//받을 공간
				}
			})	
	})//btn func
});//맨 위에 function