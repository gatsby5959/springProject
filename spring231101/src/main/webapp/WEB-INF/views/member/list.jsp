<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보리스트</title>
<style type="text/css">
.container-fluid {
	display: flex;
	flex-direction: row;
}

.pagination{
justify-content: center;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />


<%-- console.log("list로 온 값은 "+ ${list} ) --%>
<!-- console.log("bnoVal>>> " + bnoVal); -->
<%-- <h1>${list}</h1> --%>
<div class="listBox">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">email</th>
					<th scope="col">pwd</th>
					<th scope="col">nickname</th>
					<th scope="col">reg_at</th>
					<th scope="col">last_login</th>
				
				</tr>
			</thead>
	<!-- MemberVO -->
<!-- 	private String email; -->
<!-- 	private String pwd; -->
<!-- 	private String nickName; -->
<!-- 	private String regAt; -->
<!-- 	private String lastLogin; -->
<!-- 	private List<AuthVO> authVOList; -->
			<tbody>

 				<c:forEach items="${list}" var="mvo"> 
 					<tr> 
 						<th><a href="/member/detail?email=${mvo.email}">${mvo.email }</a></th> 
 						<td><a href="/member/detail?email=${mvo.email}">암호화됨</a></td>
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.nickName }</a></td> 
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.regAt }</a></td> 
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.lastLogin }</a></td>			 
 					</tr> 
				</c:forEach>
			</tbody>
		</table>

	
	
	</div>










	<jsp:include page="../common/footer.jsp" />
</body>
</html>