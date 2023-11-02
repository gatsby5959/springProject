<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<!-- email, pwd, nick_name 받기 -->

<div>
<form action="/member/register" mehtod="post">
	<h4 class="mb-3">Input your Information</h4>
	<div class="mb-3">
		<label for="e" class="form-label">email</label>
		<input type="email" class="form-contorl" name="email" id="e" placehoder="example@exampe.com">
	</div>
	<div class="mb-3">
		<label for="p" class="form-label">pwd</label>
		<input type="password" class="form-control" name="pwd" id ="p">
	</div>
	<div class="mb-3">
		<label for="n" class="form-label">닉네임</label>
		<input type="text" class="form-control" name="nickName" id ="n">
	</div>
	<button class="w-100 btn btn-primary btn-lg my-5" type="submit">가입입력</button>
</form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>