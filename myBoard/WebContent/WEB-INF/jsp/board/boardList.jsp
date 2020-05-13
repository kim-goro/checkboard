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
		${ loginUser.u_nickname }님 환영합니다.
		<a href="/boardRegmod">
			<button>글쓰기</button>
		</a>
		<a href="/profileDetail">
			<button>프로필</button>
		</a>
	</div>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>작성자</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
			<tr class="pointer trSelected" onclick="moveToDetail(${vo.i_board})">
				<td class="fontCenter">${vo.i_board }</td>
				<td>${vo.title }</td>
				<td class="fontCenter">${vo.hits }</td>
				<td class="fontCenter">${vo.r_dt }</td>
				<td class="fontCenter">					
					${vo.u_nickname }
				</td>
			</tr>
			</c:forEach>
		</table>	
		
		<div id="searchContainer">
			<form action="/boardList" method="get">
				<div>
					검색 <input type="search" name="search" value="${param.search }">
					<input type="submit" value="검색">
				</div>				
			</form>
		</div>
			
		<div id="pageContainer">
			<c:forEach var="i" begin="1" end="${totalPageCnt }">				
				<a href="/boardList?page=${i}&search=${param.search}">
				<span <c:if test="${i == page}">class="selected"</c:if>>				
				${i}
				</span>
				</a>
			</c:forEach>			
		</div>
	</div>
	<script>
		function moveToDetail(i_board) {
			location.href = '/boardDetail?i_board=' + i_board
		}
	</script>
</body>
</html>





