
 //jQuery AJAX 실습
/* 1) https://jsonplaceholder.org/posts : posts데이터 100건을 읽어서 테이블에 출력
      (id, title, thumbnail, status, category, publishedAt, updatedAt)
  2) 이미지는 이미지를 표현하고 게시일, 수정일은 24/04/02 13:35 형식으로 출력
  3) HTML 상단에 INPUT과 BUTTON 만들어서 제목으로 검색하는 기능
  4) HTML 상단에 SELECT 만들어서 카테고리별로 테이블에 출력하는 기능*/
  
/*
const categories = [];
let cidx = 0;

$(function() {
    // 검색 버튼 클릭 이벤트 처리
    $("#searchBtn").click(function() {
        const searchVal = $("#title").val(); // 검색어 가져오기
        if (searchVal != "") {
            setData(searchVal); // 검색어가 있는 경우 데이터 설정 함수 호출
        } else {
            setData("",""); // 검색어가 없는 경우 데이터 설정 함수 호출
        }
    });
    
    // 드롭다운 변경 이벤트 처리
    $("select").change(function(){
        setData($(this).val()); // 선택된 카테고리에 따라 데이터 설정 함수 호출
    });
    setData(""); // 초기 데이터 설정 함수 호출
});

// 데이터 설정 함수 정의
function setData(searchVal, currCate) {
    $("tbody").empty(); // 테이블 내용 비우기
    $.ajax({
        method: "GET",
        url: "https://jsonplaceholder.org/posts" // JSON 데이터 가져오기
    }).done(function(arr, result) {
        if (result=="success") { // 성공적으로 데이터를 가져왔을 경우
            const arrLeng = arr.length;
            for (let i=0; i<arrLeng; i++) {
                const idx = arr[i].title.indexOf(searchVal); // 검색어가 포함된 항목 찾기
                if (idx>=0) { // 검색어가 포함된 경우
                    // 테이블에 데이터 추가
                    const tr = $("<tr></tr>");
                    tr.append("<td>" + arr[i].id + "</td>");
                    tr.append("<td>" + arr[i].title + "</td>");
                    tr.append("<td><img src='" + arr[i].thumbnail + "'></td>");
                    tr.append("<td>" + arr[i].status + "</td>");
                    tr.append("<td>" + arr[i].category + "</td>");
                    if (!categories.includes(arr[i].category)) { // 카테고리 추가
                        categories[cidx++] = arr[i].category;
                    }
                    const publishedStr = moment(arr[i].publishedAt, "YY/MM/DD HH:mm")
                        .format("YY/MM/DD HH:mm"); // 게시일 포맷팅
                    tr.append("<td>" + publishedStr + "</td>");
                    const updatedStr = moment(arr[i].updatedAt, "YY/MM/DD HH:mm")
                        .format("YY/MM/DD HH:mm"); // 업데이트일 포맷팅
                    tr.append("<td>" + updatedStr + "</td>");
                    $("tbody").append(tr); // 테이블에 행 추가
                }
            }
            const categoriesLeng = categories.length;
            $("select").empty(); // 드롭다운 초기화
            $("select").append("<option value=>전체</option>"); // 전체 옵션 추가
            for (let i=0; i<categoriesLeng; i++) {
                // 카테고리 옵션 추가
                const option = $("<option value='"+categories[i]+"'>"+categories[i]+"</option>");
                $("select").append(option);
            }  
        }
    });
}
*/

/*
   1. 처음에 한번만 데이터 로딩해서 배열에 저장
   2. 기능들을 메소드로 분리
     1) 데이터 로딩해서 배열에 저장하는 기능
     2) 게시일/수정일 출력형식을 변경하는 기능
     3) 검색 기능
     4) 카테고리 추출 기능
     5) 카테고리별 출력 기능
*/

let datas = []; // 전체 포스트 저장할 배열
const categories = []; // 카테고리 저장할 배열
let categoriesIdx = 0; // 카테고리 인덱스

$(function() {

   // 처음 로딩되면 포스트 AJAX로 가져와서 출력
   getPosts();

   // 검색 이벤트 핸들러(검색하면 실행)
   $("#searchBtn").click(function() {
      printSearchPosts();
   });

   // 카테고리변경 이벤트 핸들러
   $("select").change(function() {
      printCategoryPosts();
   });
});

// AJAX 호출해서 결과를 배열로 리턴하는 함수
function getPosts() {
   $.ajax({
      method: "GET",
      url: "https://jsonplaceholder.org/posts"
   }).done(function(posts, result) {
      if (result == "success") {
         datas = posts;
         printPosts(posts);
      }
   });
}

//배열을 전달 받으면 테이블에 출력하는 함수
function printPosts(posts) {
   $("tbody").empty();
   const postsLength = posts.length;
   for (let i = 0; i < postsLength; i++) {

      setCategory(posts[i]);

      const tr = $("<tr></tr>");
      tr.append("<td>" + posts[i].id + "</td>");
      tr.append("<td>" + posts[i].title + "</td>");
      tr.append("<td><img src='" + posts[i].thumbnail + "'></td>");
      tr.append("<td>" + posts[i].status + "</td>");
      tr.append("<td>" + posts[i].category + "</td>");
      tr.append("<td>" + getDateStr(posts[i].publishedAt) + "</td>");
      tr.append("<td>" + getDateStr(posts[i].updatedAt) + "</td>");
      $("tbody").append(tr);
   }
   initCategory();
}

// 게시일/수정일 출력형식을 변경하는 기능
function getDateStr(str) {
   return moment(str, "YYYY/MM/DD HH:mm").format("YYYY/MM/DD HH:mm");
}

// 검색어에 해당하는 포스트들을 테이블에 출력하는 기능
function printSearchPosts() {
   const titleVal = $("#title").val();
   const datasLength = datas.length;
   const newDatas = [];
   let newDatasIdx = 0;
   for (let i = 0; i < datasLength; i++) {
      if (datas[i].title.includes(titleVal)) {
         newDatas[newDatasIdx++] = datas[i];
      }
   }
   printPosts(newDatas);
}

// 카테고리 설정하는 함수
function setCategory(post) {
   if (!categories.includes(post.category)) {
      categories[categoriesIdx++] = post.category;
   }
}

// 카테고리 초기화하는 함수
function initCategory() {
   $("select").empty();
   $("select").append("<option value=''>전체</option>");
   const categoriesLength = categories.length;
   for (let i = 0; i < categoriesLength; i++) {
      const option = $("<option value='" + categories[i] + "'>" + categories[i] + "</option>");
      $("select").append(option);
   }
}

// 카테고리에 해당하는 포스트 출력 함수
function printCategoryPosts() {
   const selectVal = $("select").val();
   const datasLength = datas.length;
   const newDatas = [];
   let newDatasIdx = 0;
   for (let i = 0; i < datasLength; i++) {
      if (datas[i].category == selectVal) {
         newDatas[newDatasIdx++] = datas[i];
      }
   }
   printPosts(newDatas);
}
