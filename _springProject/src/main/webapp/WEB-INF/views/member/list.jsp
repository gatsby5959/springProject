<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member list</title>
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
			<tbody>
				<c:forEach items="${list }" var="mvo">
<!-- MemberVO -->
<!-- 	private String email; -->
<!-- 	private String pwd; -->
<!-- 	private String nickName; -->
<!-- 	private String regAt; -->
<!-- 	private String lastLogin; -->
<!-- 	private List<AuthVO> authVOList; -->
					<tr>

						<th><a href="/member/detail?bno=${mvo.mno}">${mvo.email }</a></th>
						<td><a href="/member/detail?bno=${mvo.mno}">${mvo.pwd }</a></td>
						<td><a href="/member/detail?bno=${mvo.mno}">${mvo.nickName }</a></td>
						<td><a href="/member/detail?bno=${mvo.mno}">${mvo.regAt }</a></td>
						<td><a href="/member/detail?bno=${mvo.mno}">${mvo.lastLogin }</a></td>
				

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 페이징 라인 -->
<!-- 		<div> -->
<!-- 			<nav aria-label="Page navigation example"> -->
<!-- 				<ul class="pagination"> -->
					
<!-- 					이전(prev) -->
<%-- 					<li class="page-item ${(ph.prev eq false)?'disabled':''}"><a --%>
<!-- 						class="page-link" -->
<%-- 						href="/board/list?pageNo=${ph.startPage -1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" --%>
<!-- 						aria-label="Previous"> <span aria-hidden="true">&laquo;</span> -->
<!-- 					</a></li> -->
<%-- 					<c:forEach begin="${ph.startPage }" end="${ph.endPage}" var="i"> --%>
<!-- 						<li class="page-item"><a class="page-link" -->
<%-- 							href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li> --%>
<!-- 					</c:forEach> -->

<!-- 					다음(next) -->
<%-- 					<li class="page-item ${(ph.next eq false)?'disabled':''}"><a --%>
<!-- 						class="page-link" -->
<%-- 						href="/board/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" --%>
<!-- 						aria-label="Next"> <span aria-hidden="true">&raquo;</span> -->
<!-- 					</a></li> -->
<!-- 				</ul> -->
<!-- 			</nav> -->
<!-- 		</div> -->
	
	</div>
 




<jsp:include page="../common/footer.jsp" />
</body>
</html>