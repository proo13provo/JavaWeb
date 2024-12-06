<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="servlets.*"%>
<html>
<head>
<title></title>
<meta name="description" content="">
<link rel="stylesheet" href="./css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<title>Login</title>
</head>
<body>
	<form action="Login" method="POST">
		<div class="wrapper">
			<h1>Login</h1>
			<%
			String baoLoi = request.getAttribute("baoLoi") + "";
			if (baoLoi.equals("null")) {
				baoLoi = "";
			}
			%>
			<div class="messge">
				<span class="red"><%=baoLoi%></span>
			</div>
			<div class="input-box">
				<input name="username" type="text" placeholder="UserName" required>
				<i class='bx bxs-user'></i>
			</div>
			<div class="input-box">
				<input name="password" type="password" placeholder="Password"
					required> <i class='bx bxs-lock-alt'></i>
			</div>
			<div class="remember-forgot">
				<label><input type="checkbox">Remember me</label> <a
					href="#"> Forgot Password?</a>
			</div>
			<button class="bth" type="submit">Login</button>
			<div class="register-link">
				<p>
					Dont't have a account?<a href="register.jsp"> Register</a>
				</p>
			</div>
		</div>
	</form>
</body>

</html>