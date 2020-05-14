<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.circular--portrait img {
	  width: 20px;
	  height: 20px;
	  overflow: hidden;
	  border-radius: 50%;
	}
	
	.commentItem{
		display: flex;
	}
	
	.commentItem.r_dt{
		font-size:12px;
		color:#95a5a6;
	}
	
	.commentContent{
		width: 500px;
	}
</style>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
	<div>번호: ${checkboard.i_checkboard }</div>
	<div>생성자: ${checkboard.checkboard_i_user }</div>
	<div>제목: ${checkboard.checkboard_title }</div>
	<div>설명: ${checkboard.checkboard_decription }</div>
	<div>생성일: ${checkboard.r_dt }</div>
	<div>기한: ${checkboard.due_dt }</div>
	<div>상태: ${checkboard.checkboard_state }</div>
	
	<c:if test="${authUser.i_user == checkboard.i_user }">
		<div>
			<button>수정</button>
			<button onclick="clkDelBtn(${checkboard.i_checkboard})">삭제</button>
		</div>
	</c:if>
	
	<c:forEach var="goal" items="${checkboard_goal}">
		<tr class="pointer trSelected" onclick="moveToDetail(${goal.i_checkboardgoal})">
			<td  class="fontCenter"> -> ${goal.goalName}</td>
			<td class="fontCenter"><button onclick="clkDelBtn(${goal.i_checkboardgoal})">삭제</button></td>
		</tr>
	</c:forEach>
	
	<div>참가자</div>
	<table>
		<c:forEach var="vo" items="${participants}">
			<tr>
				<td class="commentItem">${participants.i_user }</td>
				<td class="conmmentDel">
					<c:if test="${vo.i_user == authUser.i_user}">
						<a href="/board/Comment.do?i_comment=${vo.i_comment}&i_board=${detail.i_board}&i_user=${vo.i_user}"><button> 삭제 </button></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	

	<div>보고하기</div>
	<div>
		<form action="/board/report.do" if="frm" method="POST" onsubmit="return chkComment()">
			<input type="hidden" name="i_checkboardgoal" value="${goal.i_checkboardgoal}">
			<input type="submit" value="보고하기">
		</form>
	</div>
	
	<script>
		function clkDelBtn(i_board) {
			var result = confirm(i_board + '번 글을 삭제하시겠습니까?')
			if(result) {
				location.href = '/boardDel?i_board=' + i_board	
			}
		}
 		function chkComment() {
			if(frm.content.value.length == 0) {
				frm.i_board.focus()
				alert('댓긇 내용을 작성해 주세요!')		
				return false
			}			
		} 
 		
 		var msg='${msg}' // 댓글 관련 기능에서 받는 응답
 		if(msg != '' && msg != undefined){
 			alert(msg)
 		}
	</script>
</body>
</html>





