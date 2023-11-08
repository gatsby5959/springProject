<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>

<h1>회원가입 페이지</h1>
<br>
<form action="/mem/register">
	ID: <input type="text" name="id"><br>
	암호: <input type="text" name="pwd"><br>
	E-MAIL: <input type="text" name="email"><br>
	AGE: <input type="text" name="age"><br>
	<button type="submit">가입</button>
</form>
</body>
</html>