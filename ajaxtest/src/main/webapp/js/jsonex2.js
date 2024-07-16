window.onload = function(){
	//실습1
	//jsonex1.html, jsonex1.js 아래URL에서 JSON데이터를 받아서 사용자 아이디:1, 할 일 아이디:1. 할 일 제목:~~, 완료여부: 완료
	//형식으로 전체 데이터를 출력하는 프로그램 //http://jsonplaceholder.typicode.com/photos
	//jsonex2	
	document.getElementById("btn").addEventListener(
		"click",
		function(){
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "http://jsonplaceholder.typicode.com/photos", true);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status==200){
					const jsonText = xhr.responseText;
					const jsonObj = JSON.parse(jsonText);
					const jsonObjLeng = jsonObj.length;
					
					let trs = "";
					
					for(let i=0; i<jsonObjLeng; i++){
						trs += `
						<tr>
							<td>${jsonObj[i].albumId}</td>
							<td>${jsonObj[i].id}</td>
							<td>${jsonObj[i].title}</td>
							<td>${jsonObj[i].url}</td>
							<td>${jsonObj[i].thumbnailUrl}</td>
						</tr>
						`;
					}//for
					document.getElementsByTagName("TBODY")[0].innerHTML = trs;
				}//if
			}//xhr
		}//function
	)//document
}