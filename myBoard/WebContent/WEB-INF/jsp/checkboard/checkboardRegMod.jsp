<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction: column;">
		<form class="solidForm" action="/checkboard/regmod.do" method="POST">
			<div class="marBottom10">
				<input type="text" name="checkboard_title" placeholder="제목">
			</div>
			<div class="marBottom10">
				<textarea name="checkboard_decription" placeholder="내용">
			</textarea></div>
			<div class="marBottom10">
				<input type="date" name="due_dt">
				</div>
			<div class="marBottom10">
				<input type="submit" value="생성하기">
			</div>
		</form>
	</div>
</body>
</html>