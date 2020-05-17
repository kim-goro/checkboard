<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEAM</title>
</head>
<body>
	<div>
		<a href="/user/profile.do"><button>친구 추가하기</button></a>
	</div>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>유저</th>
				<th>친구</th>
			</tr>
			
			<c:forEach var="friend" items="${friendList}">
				<tr>
					<td class="fontCenter">${friend.i_userRelationship }</td>
					<td class="fontCenter">${friend.i_user }</td>
					<td class="fontCenter">${friend.i_userf }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>