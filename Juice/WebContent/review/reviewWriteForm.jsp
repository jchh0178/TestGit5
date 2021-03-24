<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 글쓰기</title>
</head>
<body>
<!-- 헤더 -->

<!-- 헤더 -->

<h1> 리뷰등록 페이지 </h1>
   
   <form action="./reviewInsert.test" method="post" enctype="multipart/form-data">
   
     <table border="1">
        <tr>
        	<td>
        	  <!-- 회원이미지 가져오기 -->
        	  <img src="" >
        	</td>
	        <td>닉네임</td>
	        <td>
	          <!-- 닉네임은 readonly타입으로 수정 불가능하게 구현 
	          	   value는 표현식으로 적용-->
	          <input type="text" name="nick">
	        </td>
        </tr>
        <tr>
	        <td>리뷰내용</td>
	        <td>
	          <input type="text" name="re_content">
	        </td>
        </tr>
        <tr>
	        <td>첨부 파일</td>
	        <td>
	          <!-- 이미지 파일만 업로드 가능하게 구현 --> 
	          <input type="file" name="file">
	        </td>
        </tr>
        <tr>
	        <td colspan="2">
	          <input type="submit" value="리뷰등록">
	          <input type="reset" value="초기화">
	        </td>
        </tr>
     </table>  
   </form>

<!-- 푸터 -->

<!-- 푸터 -->
</body>
</html>