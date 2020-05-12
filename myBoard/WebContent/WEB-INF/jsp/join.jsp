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
	<!-- div class="flexContainer flexCenter">
		<form class="solidForm" id="frm" action="join" method="post" onsubmit="return submitChk()">
			<div class="marBottom10">
				<input type="text" name="u_id" placeholder="아이디" value="mic">
			</div>		
			<div class="marBottom10">
				<input type="password" name="u_pw" placeholder="비밀번호" value="1212">
			</div>
			<div class="marBottom10">
				<input type="password" name="re_pw" placeholder="비밀번호확인" value="1212">
			</div>
			<div class="marBottom10">
				<input type="text" name="u_nickname" placeholder="닉네임" value="ddd">
			</div>
			<div class="marBottom10">
				<input type="text" name="email" placeholder="이메일" value="mic@aaa.com">
			</div>
			<div class="marBottom10">
				<input type="text" name="ph" placeholder="휴대폰번호 ex(010-1234-1234)" value="010-4545-5555">
			</div>
			<div class="marBottom10">
				<input type="text" name="addr" placeholder="주소" value="대구시 달서구 와룡로 11111">
			</div>
			<div class="marBottom10">
				<label>여성<input type="radio" name="sex" value="0" checked></label>
				<label for="radio_man">남성</label><input id="radio_man" type="radio" name="sex" value="1">
			</div>
			<div class="marBottom10">
				<input type="text" name="birth" placeholder="생년월일" value="19990101">
			</div>
			<div class="flexContainer flexCenter">
				<input type="submit" value="회원가입">				
			</div>
			<div id="msg"></div>
		</form>			
	</div-->
	
	<div class="flexContainer flexCenter">
		<form class="solidForm" id="frm" action="join" method="post" onsubmit="return submitChk()">
			<div class="marBottom10">
				<input type="text" name="u_id" placeholder="아이디">
			</div>		
			<div class="marBottom10">
				<input type="password" name="u_pw" placeholder="비밀번호">
			</div>
			<div class="marBottom10">
				<input type="password" name="re_pw" placeholder="비밀번호확인">
			</div>
			<div class="marBottom10">
				<input type="text" name="u_nickname" placeholder="닉네임">
			</div>
			<div class="marBottom10">
				<input type="text" name="email" placeholder="이메일">
			</div>
			<div class="marBottom10">
				<input type="text" name="ph" placeholder="휴대폰번호 ex(010-1234-1234)">
			</div>
			<div class="marBottom10">
				<input type="text" name="addr" placeholder="주소">
			</div>
			<div class="marBottom10">
				<label>여성<input type="radio" name="sex" value="0" checked></label>
				<label for="radio_man">남성</label><input id="radio_man" type="radio" name="sex" value="1">
			</div>
			<div class="marBottom10">
				<input type="text" name="birth" placeholder="생년월일">
			</div>
			<div class="flexContainer flexCenter">
				<input type="submit" value="회원가입">				
			</div>
			<div id="msg"></div>
		</form>			
	</div>
	
	<script>
		function submitChk() {				
			if(itemChk(frm.u_id)) {				
				return false
			} else if(itemChk(frm.u_pw)) {
				return false
			} else if(frm.u_pw.value != frm.re_pw.value) {
				msg.innerHTML = '비밀번호를 확인해 주세요'
				return false
			} else if(itemChk(frm.u_nickname)) {
				return false
			} else if(itemChk(frm.email)) {
				return false
			} else if(itemChk(frm.ph)) {
				return false
			} else if(itemChk(frm.addr)) {
				return false
			} else if(itemChk(frm.birth)) {
				return false
			} else if(emailChk()) {
				return false
			}			
		}
		
		function emailChk() {
			var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
			return !emailRegExp.test(frm.email.value)
		}
		
		function itemChk(ele) {
			if(ele.value.length == 0) {
				ele.focus()
				msg.innerHTML = ele.placeholder + '을(를) 입력해 주세요'				
				return true
			}			
		}
		
		
	</script>
</body>
</html>