<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/login.css" />
</head>
<body class="text-center">
  <form class="form-signin" action="/user/login.do" autocomplete="off" method="POST">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="text" id="inputEmail" class="form-control" name="user_id" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="user_password" placeholder="Password" required>
    <div class="checkbox mb-3">
      <div style="color: red;">${Msg}</div>
      <label> <input type="checkbox" value="remember-me"> Remember me </label> &nbsp;&nbsp;&nbsp;&nbsp;
      <label><a href="#">Forgot password?</a></label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
  </form>
</body>
</html>