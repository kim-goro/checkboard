<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction: column;">
		<form class="solidForm" action="user/login.do" method="POST">
			<div class="marBottom10">
				<input type="text" name="u_id" placeholder="아이디" value="kim">
			</div>
			<div class="marBottom10">
				<input type="password" name="u_pw" placeholder="비밀번호" value="1234">
			</div>
			<div class="flexContainer flexCenter" style="height: 40px;">
				<input type="submit" value="로그인"/> &nbsp; &nbsp; 
				<a href="user/join.do">회원가입</a>
			</div>
		</form>
		<div style="color: red;">${Msg}</div>
	</div>
</body>
</html>