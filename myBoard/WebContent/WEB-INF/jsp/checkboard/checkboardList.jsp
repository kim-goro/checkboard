<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	img {
		width: 20px;
	}
	table { 
		border: 1px solid #000;
		border-collapse: collapse;
		width: 80%;
	}
	a {
		text-decoration: none;		
	}
	th, td { border: 1px solid #000; }
	.fontCenter { text-align: center; }
	.pointer { 	cursor: pointer; }
	.trSelected:hover { background-color: #ecf0f1 }
	
	#searchContainer {
		margin-top: 40px;
		display: flex;
		justify-content: center;
	}
	
	#pageContainer {
		margin-top: 40px;
		display: flex;
		justify-content: center;
	}
	#pageContainer span {
		color: #3498db;
	}
	#pageContainer a:not(:last-child) {
		margin-right: 30px;
	}
	.selected {
		font-weight: bold;
		color: red !important;
	}
</style>
</head>
<body>
	<div>
		${ authUser.user_id }님 환영합니다.
		<a href="/checkboard/regmod.do">
			<button>체크보드 생성하기</button>
		</a>
	</div>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>생성자</th>
				<th>제목</th>
				<th>설명</th>
				<th>생성일</th>
				<th>기한</th>
				<th>상태<th>
			</tr>
			
			<c:forEach var="indexMap" items="${checkboardMap}">
			<tr class="pointer trSelected" onclick="moveToDetail(${vo.i_checkboard})">
				<td class="fontCenter">${indexMap.key.i_checkboard }</td>
				<td class="fontCenter">${indexMap.key.checkboard_i_user }</td>
				<td class="fontCenter">${indexMap.key.checkboard_title }</td>
				<td class="fontCenter">${indexMap.key.checkboard_decription }</td>
				<td class="fontCenter">${indexMap.key.r_dt }</td>
				<td class="fontCenter">${indexMap.key.due_dt }</td>
				<td class="fontCenter">${indexMap.key.checkboard_state }</td>
			</tr>
			<c:forEach var="goal" items="indexMap.value">
				<tr row class="pointer trSelected">
					<td colspan="7" class="fontCenter"> -> ${vo.goalName }</td>
				</tr>
			</c:forEach>
			</c:forEach>
		</table>
	</div>
	<script>
		function moveToDetail(i_checkboard) {
			location.href = '/checkboard/detail?i_checkboard=' + i_checkboard
		}
	</script>
</body>
</html>





