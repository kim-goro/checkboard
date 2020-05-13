<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
</head>
<body>
	<div class="flexContainer flexCenter">
		<form class="solidForm" id="frm" action="user/join.do" method="post"
			onsubmit="return submitChk()">
			<div class="marBottom10">
				<input type="text" name="user_id" placeholder="*아이디">
			</div>
			<div class="marBottom10">
				<input type="password" name="user_password" placeholder="*비밀번호">
			</div>
			<div class="marBottom10">
				<input type="password" name="user_repassword" placeholder="*비밀번호확인">
			</div>
			<div class="marBottom10">
				<input type="text" name="user_email" placeholder="*이메일">
			</div>
			<div class="marBottom10">
				<label>여성<input type="radio" name="sex" value="F" checked></label>
				<label for="radio_man">남성</label> <input id="radio_man" type="radio"
					name="sex" value="M">
			</div>
			<div class="marBottom10">
				<input type='checkbox' name=user_hobby value='exercise' /> 운동 <input
					type='checkbox' name='user_hobby' value='study' /> 공부 <input
					type='checkbox' name='user_hobby' value='reading' /> 독서
			</div>
			<div class="marBottom10">
				<input type="date" name="birth" placeholder="생년월일" value="2019-09-22">
			</div>
			<div class="flexContainer flexCenter">
				<input type="submit" value="회원가입">
			</div>
			<div id="msg"></div>
		</form>
	</div>

	<script>
		function submitChk() {
			if (itemChk(frm.user_id)) {
				return false
			} else if (itemChk(frm.user_password)) {
				return false
			} else if (frm.u_pw.user_password != frm.user_repassword.value) {
				msg.innerHTML = '비밀번호를 확인해 주세요'
				return false
			} else if (itemChk(frm.u_nickname)) {
				return false
			} else if (itemChk(frm.user_email)) {
				return false
			} else if (emailChk()) {
				return false
			}
		}

		function emailChk() {
			var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
			return !emailRegExp.test(frm.user_email.value)
		}

		function itemChk(ele) {
			if (ele.value.length == 0) {
				ele.focus()
				msg.innerHTML = ele.placeholder + '을(를) 입력해 주세요'
				return true
			}
		}
	</script>
</body>
</html>