<%@page import="com.test.db.TestBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- controller & DB 연결이 잘 되는지 test하는 페이지 -->
		<a href="./getInfo.test?nick=a">DB 정보 가져오기</a><br>
		
		<%
		  /* request 객체에 저장 된 DB 정보 */
		  TestBean tb = (TestBean) request.getAttribute("info");
		  
		  if(tb != null) {
		%>
		    user num : <%=tb.getNum() %><br>
		    user email : <%=tb.getEmail() %><br>
		    user nickname : <%=tb.getNick() %><br>
		<%
		  }
		%>
	</body>
</html>