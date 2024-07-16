//jquery Ajax 실습: users데이터를 ajax로 비동기 호출하여 위도와 경도만 콘솔에 출력 
//https://jsonplaceholder.typicode.com/users
/*{
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  }*/
  $(function(){
	$.ajax({
		method: "GET",
		url: "https://jsonplaceholder.typicode.com/users",
	}).done(function(arr, status){
			if(result == "success"){
				const arrLeng = arr.length;
				for(let i = 0; i < arrLeng; i++){
					console.log("위도: "+arr[i].address.geo.lat+", 경도: " + arr[i].address.geo.lng);
				}
			}
		});
  });
  
  
  
  
  
  
  
  
  
  
  
  
  
  