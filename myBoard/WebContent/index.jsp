<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인메뉴</title>
</head>
<body>
	<u:isLogin>
		<!-- 체크보드 리스트로 가기 -->
	</u:isLogin>
	<u:notLogin>
		<jsp:forward page="/WEB-INF/jsp/user/login.jsp"/></u:notLogin>
</body>
</html>