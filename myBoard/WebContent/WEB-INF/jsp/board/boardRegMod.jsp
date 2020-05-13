<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<div>
		<form action="/boardRegmod" method="post">
			<div><input type="text" name="title" placeholder="제목"></div>
			<div><textarea name="content" placeholder="내용"></textarea></div>
			<div><input type="submit" value="저장"></div>
		</form>
	</div>
</body>
</html>