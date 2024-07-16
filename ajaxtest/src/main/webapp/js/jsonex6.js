window.onload = function(){
    document.getElementById("btn").addEventListener(
        "click",
        function(){
            // 학생 데이터 수집
            var s1name = document.getElementById("s1name").value;
            var s1kor = parseInt(document.getElementById("s1kor").value);
            var s1eng = parseInt(document.getElementById("s1eng").value);
            var s1math = parseInt(document.getElementById("s1math").value);
            
            var s2name = document.getElementById("s2name").value;
            var s2kor = parseInt(document.getElementById("s2kor").value);
            var s2eng = parseInt(document.getElementById("s2eng").value);
            var s2math = parseInt(document.getElementById("s2math").value);
            
            var s3name = document.getElementById("s3name").value;
            var s3kor = parseInt(document.getElementById("s3kor").value);
            var s3eng = parseInt(document.getElementById("s3eng").value);
            var s3math = parseInt(document.getElementById("s3math").value);

            // JSON 데이터 준비
            var data = {
                s1name: s1name, s1kor: s1kor, s1eng: s1eng, s1math: s1math,
                s2name: s2name, s2kor: s2kor, s2eng: s2eng, s2math: s2math,
                s3name: s3name, s3kor: s3kor, s3eng: s3eng, s3math: s3math
            };

            // AJAX 요청 보내기
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "http://localhost:8888/jsonex6.html", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4 && xhr.status == 200){ 
                    // 응답 처리
                    var responseJSON = JSON.parse(xhr.responseText);
                    displayScores(responseJSON); // 성적 데이터 표시 함수 호출
                }
            };
            xhr.send("s1name=" + s1name + "&s1kor=" + s1kor + "&s1eng=" + s1eng + "&s1math=" + s1math +
                     "&s2name=" + s2name + "&s2kor=" + s2kor + "&s2eng=" + s2eng + "&s2math=" + s2math +
                     "&s3name=" + s3name + "&s3kor=" + s3kor + "&s3eng=" + s3eng + "&s3math=" + s3math); // 데이터 전송
        }
    );
};

function displayScores(scores) {
    var tbody = document.getElementById("tb");
    tbody.innerHTML = ""; // 테이블 바디 초기화
    
    scores.forEach(function(student) {
        var tr = document.createElement("tr");
        
        var nameTd = document.createElement("td");
        nameTd.textContent = student.name;
        tr.appendChild(nameTd);
        
        var korTd = document.createElement("td");
        korTd.textContent = student.kor;
        tr.appendChild(korTd);
        
        var engTd = document.createElement("td");
        engTd.textContent = student.eng;
        tr.appendChild(engTd);
        
        var mathTd = document.createElement("td");
        mathTd.textContent = student.math;
        tr.appendChild(mathTd);
        
        var totalTd = document.createElement("td");
        totalTd.textContent = student.total;
        tr.appendChild(totalTd);
        
        tbody.appendChild(tr);
    });
}
