<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List Page</title>

<style type="text/css">
.container-fluid {
	display: flex;
	flex-direction: row;
}

.pagination{
justify-content: center;
}

</style>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

<!--     <title>SB Admin 2 - Tables</title> -->

    <!-- Custom fonts for this template -->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<!-- 검색 라인 -->
	<div class="container-fluid">
		<form action="/board/list" class="d-flex" role="search" method="get">
			<c:set value="${ph.pgvo.type}" var="typed"></c:set>
			<div>
				<select name="type" class="form-select" id="inputGroupSelect01">
					<option ${typed eq null?'selected':''}>Choose...</option>
					<option value="t" ${typed eq 't'?'selected':''}>title</option>
					<option value="w" ${typed eq 'w'?'selected':''}>writer</option>
					<option value="c" ${typed eq 'c'?'selected':''}>content</option>
					<option value="tw" ${typed eq 'tw'?'selected':''}>title or
						writer</option>
					<option value="tc" ${typed eq 'tc'?'selected':''}>title or
						content</option>
					<option value="cw" ${typed eq 'cw'?'selected':''}>content
						or writer</option>
					<option value="twc" ${typed eq 'twc'?'selected':''}>all</option>
				</select>
			</div>
			<div>
				<input class="form-control me-2" name="keyword"
					value="${ph.pgvo.keyword}" type="search" placeholder="Search"
					aria-label="Search">
			</div>
			<input type="hidden" name="pageNo" value="1"> <input
				type="hidden" name="qty" value="${ph.pgvo.qty}">
			<button class="btn btn-primary position-relative" type="submit">
				Search <span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount} <span class="visually-hidden">unread
						messages</span>
				</span>
			</button>
		</form>
	</div>
	
	
	<div class="listBox">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">BNO</th>
					<th scope="col">WRITER</th>
					<th scope="col">TITLE</th>
					<th scope="col">READ_CNT</th>
					<th scope="col">CMT_CNT</th>
					<th scope="col">FILE_CNT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="bvo">
					<tr>

						<th><a href="/board/detail?bno=${bvo.bno}">${bvo.bno }</a></th>
						<td><a href="/board/detail?bno=${bvo.bno}">${bvo.writer }</a></td>
						<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title }</a></td>
						<td><a href="/board/detail?bno=${bvo.bno}">${bvo.readCount }</a></td>
						<td><a href="/board/detail?bno=${bvo.bno}">${bvo.cmtQty }</a></td>
						<td><a href="/board/detail?bno=${bvo.bno}">${bvo.hasFile }</a></td>

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
<%-- 					</c:forEach> --%>

<!-- 					다음(next) -->
<%-- 					<li class="page-item ${(ph.next eq false)?'disabled':''}"><a --%>
<!-- 						class="page-link" -->
<%-- 						href="/board/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" --%>
<!-- 						aria-label="Next"> <span aria-hidden="true">&raquo;</span> -->
<!-- 					</a></li> -->
<!-- 				</ul> -->
<!-- 			</nav> -->
<!-- 		</div> -->

<div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <!-- 이전(prev) -->
            <li class="page-item ${(ph.prev eq false)?'disabled':''}"><a class="page-link"
                href="/board/list?pageNo=${ph.startPage -1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
                aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
            </a></li>
            <c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
<%--             	${ph.pgvo.pageNo} --%>
                <li class="page-item ${i == ph.pgvo.pageNo ? 'active' : ''}"><a class="page-link"
                    href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
            </c:forEach>

            <!-- 다음(next) -->
            <li class="page-item ${(ph.next eq false)?'disabled':''}"><a class="page-link"
                href="/board/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
                aria-label="Next"> <span aria-hidden="true">&raquo;</span>
            </a></li>
        </ul>
    </nav>
</div>
	
	
	
	</div>
	
	
	<script>
	// 이벤트 리스너를 추가하여 클릭된 페이지 항목에 'active' 클래스를 동적으로 추가
	document.addEventListener('DOMContentLoaded', (event) => {
	    const paginationLinks = document.querySelectorAll('.pagination .page-link');
	    
	    paginationLinks.forEach(link => {
	        link.addEventListener('click', function() {
	            // 모든 링크에서 'active' 클래스 제거
	            paginationLinks.forEach(link => {
	                link.parentElement.classList.remove('active');
	            });

	            // 클릭된 링크에 'active' 클래스 추가
	            this.parentElement.classList.add('active');
	        });
	    });
	});
	</script>
	

	<script type="text/javascript">
		const isOk = `<c:out value="${isOk}"/>`;
		if (isOk > 0) {
			alert("수정 완료");
		}

		const reisOk = `<c:out value="${reisOk}"/>`;
		if (reisOk > 0) {
			alert("삭제 완료");
		}
	</script>
	
	
	<!-- 얼럿창 관련 -->
	<script type="text/javascript">
		// 페이지가 로드될 때 실행되는 함수
		window.onload = function() {
		  // Flash attribute로 전달된 메시지가 있는지 확인
		  var message = "<c:out value='${errorMessage}'/>";
		  if(message != null && message != "") {
		    alert(message); // 메시지가 있다면 alert으로 표시
		  }
		};
	</script>
	
	

	
	
	

