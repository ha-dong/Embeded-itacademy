$(function(){
    $("#road").click(function(){
        $("#result").load("//text/hello.txt");
    });

    $("#get").click(function(){
       /* $.get("/get.jsp?name=hong&age=30", function(data, status){
			const jsonObj = JSON.parse(data);
           if(status=="success"){
				$("#result").text("이름"+jsonObj.name+", 나이: " + jsonObj);
			}
            console.log(data+","+status)
        });*/
        $.ajax({
			method: "GET",
			url: "/get.jsp?name=hong&age=30",
			data:{name: "hong", age:"30"}
		})
		.done(function(data, status){
			const jsonObj = JSON.parse(data);
			if(status=="success"){
				$("#result").text("이름"+jsonObj.name+", 나이:"+jsonObj.age);
			}
		})
    });
    
    $("#post").click(function(){
        /*$.post(
		"/get.jsp",
        {
			name: "kang",
			age: 20
		},
		function(data,status){
		const jsonObj = JSON.parse(data);
		if(status=="Success"){
			$("result").text("이름: "+jsonObj.name+",나이: "+jsonObj.age);
			
			}
		});*/
		$.ajax({
			method: "POST",
			url: "/get.jsp",
			data:{name: "hong", age:"30"}
		}).done(function(data, status){
				const jsonObj = JSON.parse(data);
				if(status=="success"){
				$("#result").text("이름"+jsonObj.name+", 나이:"+jsonObj.age);
				}	
			})
    })