<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/join.css" />
</head>
<body class="text-center">
  <form class="form-signin" action="/user/login.do" autocomplete="off" method="POST">
    <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="text" id="inputEmail" class="form-control" name="user_id" placeholder="아이디" required autofocus>
    <br>
    <label for="inputEmail" class="sr-only">Password</label>
    <input type="password" id="inputEmail" class="form-control" name="user_password" placeholder="비밀번호" required>
    <label for="inputPassword" class="sr-only">re_Password</label>
    <input type="password" id="inputPassword" class="form-control" name="user_repassword" placeholder="비밀번호 확인" required>
    
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" name="user_email" placeholder="이메일" required autofocus>
     <div style="color: red;">${Msg}</div>
    <div class="checkbox mb-3">
      <label> <input type="radio" value="user_gender" value='F' checked> 여성 </label> &nbsp;&nbsp;&nbsp;
      <label> <input type="radio" value="user_gender" value='M' checked> 남성 </label>
    </div>
    <div class="checkbox mb-3">
      <label> <input type="checkbox" value="user_hobby" value='exercise'> 운동 </label> &nbsp;&nbsp;&nbsp;
      <label> <input type="checkbox" value="user_hobby" value='study'> 공부 </label> &nbsp;&nbsp;&nbsp;
      <label> <input type="checkbox" value="user_hobby" value='reading'> 독서 </label> &nbsp;&nbsp;&nbsp;
      <label> <input type="checkbox" value="user_hobby" value='camping'> 캠핑 </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
  </form>

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