<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List Page</title>
<style type="text/css">
	.nav, .con{
		display: flex;
		justify-content: center;
	}
	
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<!-- 검색 라인 -->
	
	<div class="container-fluid con">
		<form action="/board/list" method="get" class="d-flex">
			<c:set value="${ph.pgvo.type }" var="typed"></c:set>
			<select name="type" class="form-select sel">
				<option ${typed == null? 'selected' : ''}>Choose</option>
				<option value="t" ${typed eq 't'? 'selected' : ''}>Title</option>
				<option value="w" ${typed eq 'w'? 'selected' : ''}>Writer</option>
				<option value="c" ${typed eq 'c'? 'selected' : ''}>Content</option>
				<option value="tw" ${typed eq 'tw'? 'selected' : ''}>Title+Writer</option>
				<option value="tc" ${typed eq 'tc'? 'selected' : ''}>Title+Content</option>
				<option value="wc" ${typed eq 'wc'? 'selected' : ''}>Writer+Content</option>
				<option value="twc" ${typed eq 'twc'? 'selected' : ''}>Title+Writer+Content</option>
			</select>
			<input type="text" class="form-control me-2 search" name="keyword" value="${ph.pgvo.keyword }" placeholder="검색어를 입력하세요..">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty }">
			<button type="submit" class="btn btn-primary position-relative sbtn">
			Search
			<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
   				 ${ph.totalCount }
    			<span class="visually-hidden">unread messages</span>
  			</span>
			</button>
		</form>
	</div>
	
	<div class="container-fluid con">
	<table class="table table-dark table-striped ta">
		<thead>
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>수정일</th>
				<th>조회수</th>
				<th>댓글개수</th>
				<th>파일개수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/cntdetail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.regAt }</td>
					<td>${bvo.modAt }</td>
					<td>${bvo.readCount }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.hasFile }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
	<!-- 페이징 라인 -->
	<nav aria-label="Page navigation example" class="nav">
		<ul class="pagination">
			<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
				<a class="page-link" href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">Previous</a>
			</li>
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				   <li class="page-item">
					    <a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a>
				   </li>
			</c:forEach>
			<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
				<a class="page-link" href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">Next</a>
			</li>
  		</ul>
	</nav>
	
	<jsp:include page="../common/footer.jsp" />
</body>
</html>