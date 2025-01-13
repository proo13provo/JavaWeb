<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác thực Email</title>
</head>
<body>
<h2>Xác thực Email</h2>
<p>Chúng tôi đã gửi mã xác thực đến email của bạn. Vui lòng kiểm tra và nhập mã xác thực:</p>

<form action="${pageContext.request.contextPath}/verify-email" method="post">
    <input type="text" name="verificationCode" placeholder="Nhập mã xác thực">
    <button type="submit">Xác nhận</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>