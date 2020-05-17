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

<table>
	<tr>
		<th>유저 번호</th>
	</tr>
	
	<form class="solidForm" action="/checkboard/AddParticipant.do" method="POST">
		<c:forEach var="vo" items="${list}">
			<tr>
				<c:if test="{&i_checkboard != 0}"> <td><input type='checkbox' name=i_user value="${vo.i_user}"></td> </c:if>
				<td class="fontCenter">${vo.i_user}</td>
			</tr>
		</c:forEach>
		<c:if test="{&i_checkboard != 0}">
			<div>추가하기</div>
			<div>
				<input type="hidden" name="i_checkboard" value="${i_checkboard}">
				<input type="submit" value="추가하기">
			</div>
		</c:if>
	</form>
</table>

</body>
</html>