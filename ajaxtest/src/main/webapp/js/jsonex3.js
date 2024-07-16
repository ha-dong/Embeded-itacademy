window.onload = function(){
	//실습1
	//jsonex1.html, jsonex1.js 아래URL에서 JSON데이터를 받아서 사용자 아이디:1, 할 일 아이디:1. 할 일 제목:~~, 완료여부: 완료
	//형식으로 전체 데이터를 출력하는 프로그램 //http://jsonplaceholder.typicode.com/todos
	
	document.getElementById("btn").addEventListener(
		"click",
		function(){
			const xhr = new XMLHttpRequest();
			
			xhr.open("GET", "http://localhost:8888/ajaxtest/person.jsp?name=홍길동&age=25", true);
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status==200){
					const jsonText = xhr.responseText;
					const jsonObj = JSON.parse(jsonText);
					document.getElementById("result").innerHTML
					 = "이름: " + jsonObj.name + ", 나이: " + jsonObj.age;
					
				}//if
			}///xhr
		}//function
	)//document
}