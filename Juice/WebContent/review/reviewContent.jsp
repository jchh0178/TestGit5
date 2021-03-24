<%@page import="com.test.db.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 내용 페이지</title>
</head>
<body>
<!-- 헤더 -->

<!-- 헤더 -->

<h1> 리뷰등록 페이지 </h1>
	
	<%
	  ReviewBean rb = (ReviewBean)request.getAttribute("rb");
	  System.out.println(rb);
	%>
   
   <form action="" method="post" enctype="multipart/form-data">
     <table border="1">
        <tr>
          	<td>
              <!-- 리뷰번호 -->
              <%=rb.getRe_num() %>
          	</td>
        </tr>
     	<tr>
     		<td>
     	      <!-- 별점 -->
     	      <%=rb.getRe_star() %>
     	  	</td>
     	</tr>
        <tr>
	        <td>
	          <!-- 회원이미지 -->
	          <img src="">
	        </td>
	        <td><%=rb.getNick() %></td>
        </tr>
        <tr>
	        <td>리뷰내용</td>
	        <td>
	          <%=rb.getRe_content() %>
	        </td>
        </tr>
        <tr>
	        <td>첨부 파일</td>
	        <td>
	          <!-- 이미지 파일 -->
	          <img src="./upload/<%=rb.getFile()%>">
	        </td>
        </tr>
        <tr>
        	<td>
        	  <!-- 리뷰 등록날짜 -->
        	  <%=rb.getRe_date() %>
        	</td>
        </tr>
        <% %>
        <tr>
	        <td colspan="2">
	          <input type="submit" value="리뷰 수정">
	          <input type="reset" value="리뷰 삭제">
	        </td>
        </tr>
        
     </table>  
   </form>

<!-- 푸터 -->

<!-- 푸터 -->
</body>
</html>