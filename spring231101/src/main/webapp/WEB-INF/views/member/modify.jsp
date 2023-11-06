<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

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
	<form action="/member/modify" method="post">
	<div class="bigBox">
		<table class="table">

			<tr>
				<th scope="col">Email</th>
				<td>${mvo.email}</td><!-- -->
			</tr>

			<tr>
<!-- 				<th scope="col">PWD</th> -->
<!-- 				<td> -->
<%-- 					<input type="text" class="form-control"  name="pwd" id="p" value="${mvo.pwd}">		 --%>
<!-- 				</td> -->
<!-- 			</tr> $2a$10$29D5swWSM7v9jqSycKcuPuB0m3R5rhP9MeGnk7Mo1jE9ofl2UBNhO-->
			
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
				<td>${mvo.lastLogin }</td><!-- bdto.삭제 전경환231025_00:40-->
			</tr>
		</table>
		
		<%-- 파일 표시란 --%>
<%-- 		<c:set value = "${bdto.flist}" var ="flist"></c:set><!-- 난 안쓰는 부분인듯 --> --%>
<%-- 		<c:forEach items="${bdto.flist}" var="fvo"> <!-- bdto.삭제 전경환231025_00:40--> --%>
<%-- 			<c:choose> -->
<%-- 				<c:when test="${fvo.fileType > 0}"> --%>
<%--	  			<div> -->
<%-- 						<a href="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_${fvo.fileName}"> --%>
<%-- 							<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}"> --%>
<%-- 						</a> 원본파일명 규칙 th해봄							 --%>
<%--					</div> --%>
<%--				</c:when> --%>
<%-- 				<c:otherwise> --%>
<%-- 					이미지 없은 경우 아이콘 표시 --%>
<%--				</c:otherwise> --%>
<%-- 			</c:choose> --%>
<%-- 			<div class="ms-2 me-auto"> --%>
<%-- 				<div class="fw-bold">${fvo.fileName }</div> --%>
<%-- 				<span class="badge bg-secondary rounded-pull">${fvo.fileSize}Byte</span> --%>
<%-- 			</div> --%>
<%-- 		</c:forEach> --%>

<%-- 		<a href="/board/modify?bno=${bvo.bno}"><button	class="btn btn-primary" type="button">수정</button></a>  --%>
<%-- 		<a	href="/board/remove?bno=${bvo.bno}"><button	class="btn btn-primary" type="button">삭제</button></a>  --%>
<%-- 		<a	href="/board/list"><button class="btn btn-primary" type="button">리스트로</button></a> --%>
<!-- 		<hr> -->
		
<%-- 		<a href="/member/modify?email=${mvo.email}"><button	class="btn btn-primary" type="button">수정완료!</button></a>  --%>
		
		<input type="hidden" name="email" value="${mvo.email}">
    <button class="btn btn-primary" type="submit">수정완료!</button>
<%-- 		<a href="/member/remove?email=${mvo.email}"><button	class="btn btn-primary" type="button">삭제</button></a>  --%>
		<a href="/member/list"><button class="btn btn-primary" type="button">리스트로</button></a>
		


		</div>
		</form>

		<jsp:include page="../common/footer.jsp" />
</body>




</html>