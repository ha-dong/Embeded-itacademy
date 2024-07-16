window.onload = function(){
	document.getElementById("btn").addEventListener(
		"click",
		function(){
			
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "http://localhost:8888/ajaxtest/assets/hello.json", true);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					const jsonText = xhr.responseText;
					const jsonObj = eval("("+ jsonText + ")");
					const jsonObjLeng = jsonObj.length;
					
					let printStr = "";
					
					for(let i=0; i<jsonObjLeng; i++){
						const person = jsonObj[i];
						const name = person.name;
						printStr += "이름: " + person.name + ", 나이: " + person.age;
					}
					document.getElementById("result").innerHTML = printStr;
				}
			}
		}
	);
}