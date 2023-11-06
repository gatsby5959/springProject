<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원들의 세부 정보</title>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="bigBox">
	<table class="table">
		<tr>
			<th scope="col">Email</th>
			<td>${mvo.email}</td>
		</tr>
		<tr>
			<th scope="col">NICK_NAME</th>
			<td>${mvo.nickName  }</td>
		</tr>
		<tr>
			<th scope="col">REG_AT</th>
			<td>${mvo.regAt }</td>
		</tr>
		<tr>
			<th scope="col">LAST_LOGIN</th>
			<td>${mvo.lastLogin }</td>
		</tr>
	</table>
	<a href="/member/modify?email=${mvo.email}"><button class="btn btn-primary" type="button">수정</button></a>
<%-- 	<a href="/member/remove?email=${mvo.email}"><button	class="btn btn-primary" type="button">삭제</button></a>  --%>
<!-- 	<a href="/member/list"><button class="btn btn-primary" type="button">리스트로</button></a> -->
		
</div>




<jsp:include page="../common/footer.jsp" />
</body>
</html>