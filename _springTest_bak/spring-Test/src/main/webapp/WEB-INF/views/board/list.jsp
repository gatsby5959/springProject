<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- 검색라인S -->
<!-- serach구역 S -->
<div>
	<c:set value="${ph.pgvo.type}" var="typed"></c:set>
	<form action="/board/list" method = "get"
	 		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
		<div class="input-group">
				<select name = "type"  style="margin-right: 10px; > <!--class="form-control bg-light border-0 small" -->
				<option ${typed == null ? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't'?'selected':''}>title</option>
				<option value="w" ${typed eq 'w'?'selected':''}>writer</option>
				<option value="c" ${typed eq 'c'?'selected':''}>content</option>
				<option value="tw" ${typed eq 'tw'?'selected':''}>title+writer</option>
				<option value="tc" ${typed eq 'tc'?'selected':''}>title+content</option>
				<option value="twc" ${typed eq 'twc'?'selected':''}>title+writer+content</option>
			</select>
			
			<input type="text"  class="form-control bg-light border-0 small" name="keyword" value="${ph.pgvo.keyword}"
			placeholder="Search....." aria-label="Search" aria-describedby="basic-addon2"><!-- 이미 ph.pgvo 에서 .keyword .pageNo .qty등이 계산이 되어 있는듯 -->
			<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo}"> <!-- 컨트롤로에서 이미 ph를 set해줌 -->
			<input type="hidden" name="qty" value="${ph.pgvo.qty }"> <!-- 객체.객체.변수   개념같음 --><!-- 								<button type="submit" class="btn btn-outline-success">검색</button> -->
		 	<div class="input-group-append">
				<button class="btn btn-primary" type="submit">
                    <i class="fas fa-search fa-sm"></i>
                    찾기Search
                </button>
             </div>
		</div>
	</form>
</div>
<!-- serach구역 E -->
<!-- 검색라인E -->

<table class="table hover">
<thead>
<tr>
	<th>#</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
	<th>댓글수</th><!-- 231016추가 -->
	<th>파일수</th><!-- 231016추가 -->
</tr>
</thead>
<tbody>
<c:forEach items="${list }" var = "bvo">
	<tr>
		<td>${bvo.bno }</td>
		<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
		<td>${bvo.writer }</td>
		<td>${bvo.registerDate }</td>
		<td>${bvo.read_count }</td>
		<td>${bvo.commentCount }</td> <!-- 231016추가 -->
		<td>${bvo.fileCount }</td><!-- 231016추가 -->
	</tr>
</c:forEach>
</tbody>
</table>


<!-- 페이징라인S -->
<%-- 페이지네이션 표시구역S --%>
<div>
	<%--prev(이전페이지) ◁ | --%>
	<c:if test="${ph.prev}">          <!-- 바로앞페이지로 감 ◁ | -->
		<a href ="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">◁ |</a> 
		
		
	</c:if>
	
	<c:forEach begin="${ph.startPage}" end = "${ph.endPage}" var="i">
		<a href = "/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
	</c:forEach>
	
	<c:if test= "${ph.next}"> <!-- 바로 음페이지로 감 | ▷ -->
		<a href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">| ▷</a>
	</c:if>	
</div>
<%-- 페이지네이션 표시구역E --%>
<!-- 페이징라인E -->


<script type="text/javascript">
   const isOk = `<c:out value="${isOk}" />`;
   console.log(isOk);
   if(isOk==1){
      alert("삭제 완료!");
   }
</script>



<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>