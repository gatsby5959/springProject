<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 페이지입니다.</title>
</head>
<body>
<h3>로그아웃하려고 들어오는 페이지</h3>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>


<div class="container">
	<form action="/member/login" method="post">
		<h4 class="mb-3">Custom Logout Page</h4>
		<div class="mb-3">
		  <label for="e" class="form-label">email</label>
		  <input type="email" class="form-control" name="email" id="e" placeholder="example@example.com">
		</div>
		<button class="w-100 btn btn-primary btn-lg my-5" type="submit">log out</button>
	</form>
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>