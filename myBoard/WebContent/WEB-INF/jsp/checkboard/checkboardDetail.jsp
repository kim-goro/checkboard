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
	
	<c:if test="${authUser.i_user == checkboard.checkboard_i_user }">
		<div>
			<button>체크보드 수정</button>
			<button onclick="clkDelBtn(${checkboard.i_checkboard})">체크보드 삭제</button>
		</div>
	</c:if>
	
	
	
	<div>보고자</div>
	<c:forEach var="report" items="${checkboard_report}">
		<table>
			<tr>
				<td> 보고자 </td>
				<td> 목표 </td>
				<td> 상태 </td>
				<td> 날짜 </td>
			</tr>
			<tr>
				<td>${report.i_user}</td>
				<td>${report.i_checkboardgoal}</td>
				<td>${report.goalState}</td>
				<td>${report.r_dt}</td>
			</tr>
		</table>
	</c:forEach>
	<form class="solidForm" id="frm" action="/checkboard/report.do" method="POST" onsubmit="return chkReport()">
		<table>
			<c:forEach var="goal" items="${checkboard_goal}">
				<tr>
					<td><input type='checkbox' name=i_checkboardgoal value="${goal.i_checkboardgoal}"></td>
					<td class="fontCenter"> -> ${goal.goalName}</td>
				</tr>
			</c:forEach>
		</table>
		<div>보고하기</div>
		<div>
			<input type="hidden" name="i_checkboard" value="${checkboard.i_checkboard}">
			<input type="submit" value="보고하기">
		</div>
	</form> 
	
	<c:if test="${checkboard.checkboard_i_user == authUser.i_user}">
		<div>참가자</div>
		<table>
			<c:forEach var="pa" items="${participants}">
				<tr>
					<td class="commentItem">${pa.i_user}</td>
					<td class="conmmentDel"></td>
				</tr>
			</c:forEach>
		</table>
		<a href="/user/FriendList.do?i_checkboard=${checkboard.i_checkboard}">참가자 추가하기</a>
	</c:if>
	
	<script>
		function clkDelBtn(i_checkboard) {
			var result = confirm(i_checkboard + '번 체크보드를 삭제하시겠습니까?')
			if(result) {
				location.href = '/checkboard/Del.do?i_checkboard=' + i_checkboard	
			}
		}
 		function chkReport() {
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





