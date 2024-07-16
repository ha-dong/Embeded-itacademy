window.onload = function(){
	//실습1
	//jsonex1.html, jsonex1.js 아래URL에서 JSON데이터를 받아서 사용자 아이디:1, 할 일 아이디:1. 할 일 제목:~~, 완료여부: 완료
	//형식으로 전체 데이터를 출력하는 프로그램 //http://jsonplaceholder.typicode.com/todos
	
	document.getElementById("btn").addEventListener(
		"click",
		function(){
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "http://jsonplaceholder.typicode.com/todos", true);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status==200){
					const jsonText = xhr.responseText;
					const jsonObj = JSON.parse(jsonText);
					const jsonObjLeng = jsonObj.length;
					
					let printStr = "";
					
					for(let i=0; i<jsonArrLeng; i++){
						printStr += '사용자아이디:${jsonObj[i].userId},';
						printStr += '할일아이디:${jsonObj[i].id},';
						printStr += '할일제목:${jsonObj[i].title},';
						printStr += '완료여부:${jsonObj[i].completed==true ? "완료":"미완료"}<br/>';
					}//for
					document.getElementById("result").innerHTML = printStr;
					
				}//if
			}///xhr
		}//function
	)//document
}