<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  .custom-select {
    width: 190px; /* 원하는 너비 설정 */
    display: inline-block; /* 요소를 인라인 블록으로 표시하여 줄바꿈 방지 */
  }

  .custom-input {
    width: 190px; /* 원하는 너비 설정 */
    display: inline-block; /* 요소를 인라인 블록으로 표시하여 줄바꿈 방지 */
  }
</style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<div class="container my-3">
<h2>Product List
<a href="/board/register" class="btn btn-outline-primary">REG</a>
</h2>
<!-- 검색라인S -->


<div class="input-group mb-3">
  <form action="/board/list" method="get" style="width: 800px;">
    <c:set value="${ph.pgvo.type}" var="typed" />
    <select class="custom-select me-2" name="type" id="inputGroupSelect01">
     	<option ${typed eq null  ? 'selected' : '' } >Choose...</option>
	    <option value="t" ${typed eq 't' ? 'selected':'' }  >Title</option>
	    <option value="w" ${typed eq 'w' ? 'selected':'' }  >Writer</option>
	    <option value="c" ${typed eq 'c' ? 'selected':'' }  >Content</option>
	    <option value="tw" ${typed eq 'tw' ? 'selected':'' }  >Title or Writer</option>
	    <option value="tc" ${typed eq 'tc' ? 'selected':'' }  >Title or Content</option>
	    <option value="cw" ${typed eq 'cw' ? 'selected':'' }  >Content or Writer</option>
	    <option value="twc" ${typed eq 'twc' ? 'selected':'' } >all</option>
    </select>
    <input class="custom-input me-2" name="keyword" value="${ph.pgvo.keyword}" type="search" placeholder="Search" aria-label="Search">
          <input type="hidden" name="pageNo" value="1">
      <input type="hidden" name="qty" value="${ph.pgvo.qty }">
    <button class="btn btn-outline-success" type="submit">
      Search
      <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
        ${ph.totalCount}
        <span class="visually-hidden">unread messages</span>
      </span>
    </button>
  </form>
</div>



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
<c:forEach items="${list}" var = "bvo">
	<tr>
		<td>${bvo.bno }</td>
		<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
		<td>${bvo.writer }</td>
		<td>${bvo.regAt }</td>
		<td>${bvo.readCount }</td> <!-- 231016추가 -->
		<td>${bvo.cmtQty }</td><!-- 231016추가 -->
		<td>${bvo.hasFile }</td>
	</tr>
</c:forEach>
</tbody>
</table>


<!-- 페이징라인S -------------------------------->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!-- 이전S -->
    <li class="page-item  ${ph.prev eq false ? 'disabled' : '' }" >
      <a class="page-link" href="/board/list?pageNo=${ph.startPage - 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous"> <!-- 예시) 11페이지에서 1개 뺴서 10페이지 보여줌 -->
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="${ph.startPage }" end = "${ph.endPage}" var = "i"> <!-- 스타트부터 앤드까지 i로 저장 -->
		<!--      반복적으로 나와야 하는 라인 (1 2 3 4 5 6 7 8 9 10)-->
		<!--     <li class="page-item"><a class="page-link" href="#">1</a></li> -->
		<!--     <li class="page-item"><a class="page-link" href="#">2</a></li> -->
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}">${i}</a></li>
	</c:forEach>
    <!-- 다음S -->
    <li class="page-item  ${ph.next eq false ? 'disabled' : '' }">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<!-- 페이징라인E -------------------------------->
</div>

<script type="text/javascript">
   const isOk = `<c:out value="${isOk}" />`;
   console.log(isOk);
   if(isOk==1){
      alert("삭제 완료!");
   }
</script>



<jsp:include page = "../common/footer.jsp"/>
</body>
</html>