window.onload = function(){

	document.getElementById("btn").addEventListener(
		"click",
		function(){
			const name = document.getElementById("name").value;
			const age = document.getElementById("age").value;
			
			xhr.open("POST", "http://localhost:8888/ajaxtest/person.jsp", true);
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhr.send(`name=${name}&age=&{age}`);//이렇게 하면 입력한 걸 보내개 된다.
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState==4&&xhr.status==200){ 
					
					const jsonText = xhr.responseText; 
					const jsonObj = eval("("+jsonText+")");
					document.getElementById("result").innerHTML
					 = "이름: " + jsonObj.name + ", 나이: " + jsonObj.age;
				}//if
			}///xhr
		}//function
	)//document
}