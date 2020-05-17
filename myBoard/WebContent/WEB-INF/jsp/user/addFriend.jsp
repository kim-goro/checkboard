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
	<!-- 체크보드에 유저 추가 -->
	<c:if test="${i_checkboard ne 0 && i_checkboard ne null}"> 
	<form class="solidForm" action="/checkboard/AddParticipant.do" method="POST">
		<c:forEach var="vo" items="${list}">
			<tr>
				<c:if test="${i_checkboard != 0}"> <td><input type='checkbox' name=i_user value="${vo.i_user}"></td> </c:if>
				<td class="fontCenter">${vo.i_user}</td>
			</tr>
		</c:forEach>
		<c:if test="${i_checkboard != 0}">
			<div>추가하기</div>
			<div>
				<input type="hidden" name="i_checkboard" value="${i_checkboard}">
				<input type="submit" value="추가하기">
			</div>
		</c:if>
	</form>
	</c:if>
	
	<!-- 검색된 유저  -->
	<c:if test="${i_checkboard eq 0 || i_checkboard eq null}"> 
	<form class="solidForm" action="/user/AddFriend.do" method="POST">
		<c:forEach var="vo" items="${list}">
			<tr>
				<c:if test="${i_checkboard != 0}"> <td><input type='checkbox' name=i_user value="${vo.i_user}"></td> </c:if>
				<td class="fontCenter">${vo.i_user}</td>
			</tr>
		</c:forEach>
		<div>추가하기</div>
		<div>
			<input type="submit" value="추가하기">
		</div>
	</form>
	</c:if>
</table>

</body>
</html>