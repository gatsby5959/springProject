<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/test.css">
    <title>Your Page Title</title>
</head>
<body>

<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="/mem/register">
			<h1>계정 생성</h1>
			<div class="social-container">
			
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			
			</div>
			<span>or use your email for registration</span>
			<%--ID: --%> 
			<%--암호: --%> 
			<%--E-MAIL: --%> 
			<%--AGE: --%> 
			
			
			<input type="text" placeholder="ID" name="id" />
			<input type="text" placeholder="암호" name="pwd" />
			<input type="text" placeholder="E-MAIL" name="email" />
			<input type="text" placeholder="AGE" name="age" />
			
			<button>회원가입</button>
		</form>
	</div>
	
	<div class="form-container sign-in-container">
		<c:if test="${ses.id eq null }">
		<form action="/mem/login" method="post">
			<h1>Sign in</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
			<span>or use your account</span>
			<input type="text" name="id" placeholder="아이디" />
			<input type="text" name="pwd" placeholder="암호" />
			<a href="#">Forgot your password?</a>
			<button>로그인</button>
		</form>
		</c:if>
		
	<div>
	
 <c:if test="${ses.id ne null }"><%--세션이 있으면 보여줘라--%>
<div class="user-info-container">
 
    <div class="user-info">
      <p>${ses.id } 님, 로그인 하였습니다</p>
      <p>계정 생성일 : ${ses.regdate }</p>
      <p>마지막 접속 : ${ses.lastlogin }</p>
    </div>
    <div class="user-actions">
      <a href="/mem/modify" class="btn btn-primary">회원정보수정</a>
      <a href="/mem/list" class="btn btn-success">회원리스트</a>
      <a href="/mem/logout" class="btn btn-danger">(회원당사자)로그아웃</a>
      <a href="/brd/register" class="btn btn-info">게시판 글쓰기</a>
      <a href="/brd/pageList" class="btn btn-info">게시판 리스트</a>
    </div>
  
</div>
</c:if>

	</div>
		
	</div>
	
<%-- 	<c:if test="${ses.id eq null }"> 세션이 없으면 보여줘라 --%>
<%--  		<form action="/mem/login" method="post"> --%>
<%-- 		ID : <input type="text" name="id">	 --%>
<%-- 		PWD : <input type="text" name="pwd"> --%>
<%-- 		<button type="submit">LOGIN</button> action="/mem/login"  간다 --%>
<%-- 		</form> --%>
<%-- 	</c:if> --%>
	
	
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<c:if test="${ses.id eq null }"><%--세션이 없으면 보여줘라--%>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">회원가입</button>
				</c:if>
			</div>
		</div>
	</div>
</div>



    <script type="text/javascript" src="/test.js"></script>
</body>
</html>