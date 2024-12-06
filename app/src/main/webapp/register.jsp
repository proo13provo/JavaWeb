<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<link rel="stylesheet" href="./css/style.css">
<title>register</title>
</head>
<body>
	<form action="Login" method="POST">
		<div class="wrapper">
			<h1>Register</h1>
			<div class="input-box">
				<input name="username" type="text" placeholder="UserName" required>
				<i class='bx bxs-user'></i>
			</div>
			<div class="input-box">
				<input name="email" type="text" placeholder="Email" required>
				<i class='bx bx-envelope'></i>
			</div>
			<div class="input-box">
				<input name="password" type="password" placeholder="Password"
					required> <i class='bx bxs-lock-alt'></i>
			</div>
			<div class="input-box">
				<input name="password" type="password" placeholder="Confirm Password"
					required> <i class='bx bxs-lock-alt'></i>
			</div>
			<button class="bth" type="submit">Register</button>
			<div class="singin-link">
			<p>
					you already have account! <a href="Login.jsp"> Register</a>
				</p>
			</div>
		</div>
	</form>
</body>
</html>