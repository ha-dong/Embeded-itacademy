window.onload = function(){
	document.querySelector("#btn").addEventListener("click", function(){
		
		const num1 = document.querySelector("input[name='num1']").value;
		const num2 = document.querySelector("input[name='num2']").value;
		
		if(!num1 || isNaN(num1)){
			alert('첫 번째 숫자를 입력해');
			return false;
		}
		if(!num2 || isNaN(num2)){
			alert('두 번째 숫자를 입력해');
			return false;
		}
		const result = document.querySelector("input[name='result']").value;
			if(!result){
				alert('연산자를 입력')
				return false;
			}
			if(result!= '+' && result!= '-' && result!= '*' && result!= '/'){
				alert('연산자는 +,-,*,/ 중에서 입력')
				return false;
			}
			document.querySelector("form[name='form']").submit();
	});
}