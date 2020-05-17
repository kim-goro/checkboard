<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction: column;">
		<form class="solidForm" action="/user/search.do" method="POST">
			<div class="marBottom10">
				<input type="text" name="user_id" placeholder="아이디" value="kim">
			</div>
			<div class="flexContainer flexCenter" style="height: 40px;">
				<input type="submit" value="검색하기"/> &nbsp; &nbsp; 
			</div>
		</form>
		<div style="color: red;">${Msg}</div>
	</div>
</body>
</html>