<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정페이지</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	
	<form action="/member/modify" method="post">
	<div class="bigBox">
		<table class="table">
			<tr>
				<th scope="col">Email</th>
				<td>${mvo.email}</td>
			</tr>
			
			<tr>
				<th scope="col">NICK_NAME</th>
				<td>
					<input type="text" class="form-control"  name="nickName" id="n" value="${mvo.nickName}">
				</td>
			</tr>
			
			<tr>
				<th scope="col">REG_AT</th>
				<td>${mvo.regAt }</td><!-- -->
			</tr>
			<tr>
				<th scope="col">LAST_LOGIN</th>
				<td>${mvo.lastLogin }</td>
			</tr>
		</table>
		<input type="hidden" name="email" value="${mvo.email}">
    	<button class="btn btn-primary" type="submit">수정완료!</button>
    	<a href="/member/list"><button class="btn btn-primary" type="button">멤버리스트로</button></a>
	
	</div>
	</form>
	
	
	<jsp:include page="../common/footer.jsp" />
</body>
</html>