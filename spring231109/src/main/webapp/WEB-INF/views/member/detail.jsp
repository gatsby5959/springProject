<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>	  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- <body> -->
<!-- 일단 멤버 디테일 jsp진입 -->
<!-- <script> -->
<%--  console.log("mvo는 " + "${mvo}"); --%>
<!-- </script> -->
<!-- </body> -->

<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
<%-- 	console.log("mvo는 " + "${mvo}"); --%>
	
<!-- 		private String email; -->
<!-- 	private String pwd; -->
<!-- 	private String nickName; -->
<!-- 	private String regAt; -->
<!-- 	private String lastLogin; -->
<!-- 	private List<AuthVO> authVOList; -->
	
	<div class="bigBox">
		<table class="table">

			<tr>
				<th scope="col">Email</th>
				<td>${mvo.email }</td><!-- bdto.삭제 전경환231025_00:40-->
			</tr>
<%-- 			<tr> --%>
<%-- 				<th scope="col">PWD</th> --%>
<%-- 				<td>${mvo.pwd }</td>  bdto.삭제 전경환231025_00:40 --%>
<%-- 			</tr> --%>
			<tr>
				<th scope="col">NICK_NAME</th>
				<td>${mvo.nickName  }</td><!-- bdto.삭제 전경환231025_00:40-->
			</tr>
			<tr>
				<th scope="col">REG_AT</th>
				<td>${mvo.regAt }</td><!-- bdto.삭제 전경환231025_00:40-->
			</tr>
			<tr>
				<th scope="col">LAST_LOGIN</th>
				<td>${mvo.lastLogin }</td><!-- bdto.삭제 전경환231025_00:40-->
			</tr>
		</table>
		

		
		<a href="/member/modify?email=${mvo.email}"><button	class="btn btn-primary" type="button">수정</button></a> 
		<a href="/member/remove?email=${mvo.email}"><button	class="btn btn-primary" type="button">삭제</button></a> 
<%-- 		${mvo.authVOList.auth} --%>
<%-- 		<c:if test="${mvo.authVOList.auth == 'ROLE_ADMIN'}"> --%>
<!-- 		    <a href="/member/list"><button class="btn btn-primary" type="button">멤버 리스트로</button></a> -->
<%-- 		</c:if> --%>

		<sec:authentication property="principal.mvo.authVOList" var="auths"/>
		
<!-- 		<h3>test_1</h3> -->
<%-- 		<h3>${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}</h3> --%>
<%-- 		<h3>test_2 ${mvo.authVOList} ${mvo.email} ${mvo}</h3> --%>
	
			<h3>test_3</h3>
			    <c:if test = "${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
			        <a href="/member/list"><button class="btn btn-primary" type="button">멤버 리스트로</button></a>
			    </c:if>
			    

		



		</div>

		<jsp:include page="../common/footer.jsp" />
</body>




</html>