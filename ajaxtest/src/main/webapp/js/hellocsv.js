window.onload = function(){
	document.getElementById("btn").addEventListener(
		"click",
		function(){
			
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "http://localhost:8888/ajaxtest/assets/hello.csv", true);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					const csv = xhr.responseText;
					const csvArr = csv.split(",");
					const csvArrLeng = csv.length;
					
					let printStr = "";
					
					for(let i=0; i<csvArrLeng; i++){
						printStr += csvArr[i] + " ";
					}
					
					document.getElementById("result").innerHTML = printStr;
				}
			}
		}
	);
}