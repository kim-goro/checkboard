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

	<div>제목: ${detail.title }</div>
	<div>조회수: ${detail.hits }</div>
	<div>작성일시: ${detail.r_dt }</div>
	<div>수정일시: ${detail.m_dt }</div>
	<div>작성자: ${detail.u_nickname }</div>
	<div>내용</div>
	<div>${detail.content }</div>
	<c:if test="${loginUser.i_user == detail.i_user }">
		<div>
			<button>수정</button>
			<button onclick="clkDelBtn(${detail.i_board})">삭제</button>
		</div>
	</c:if>
	<div>
		<form action="/boardComment" if="frm" method="POST"
			onsubmit="return chkComment()">
			<input type="hidden" name="i_board" value="${detail.i_board}">
			<textarea rows="6" cols="20" name="content" placeholder="댓글달기"></textarea>
			<input type="submit" value="등록">
		</form>
	</div>
	<div>댓글리스트</div>
	
	<table>
		<c:forEach var="vo" items="${commentList}">
			<tr>
				<td class="circular--portrait commentItem">
					<c:if test="${vo.user_img eq null}">
						<img src="/img/noProfile.png" alt="프로필 이미지 없음">
					</c:if> 
					<c:if test="${vo.user_img != null}">
						<a href="/profileImgs"> <img
							src="/img/${vo.i_user}/${vo.user_img}" alt="프로필 이미지">
						</a>
					</c:if>
				</td>
				<td class="commentItem">${vo.user_nm }</td>
				<td class="commentItem">${vo.content }</td>
				<td class="commentItem">${vo.r_dt }</td>
				<td class="conmmentDel">
					<c:if test="${vo.i_user == loginUser.i_user}">
						<a href="/boardComment?i_comment=${vo.i_comment}&i_board=${detail.i_board}&i_user=${vo.i_user}"><button> 삭제 </button></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
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





