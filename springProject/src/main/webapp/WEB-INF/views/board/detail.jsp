<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>


<%-- <c:set value="${boardDTO.bvo}" var="bvo"></c:set> <!-- bvo을 넣어줄꼐요?231016 --> --%>
<c:set value="${bvo}" var="bvo"></c:set>
<table class="table table-hover">
    <thead>
        <!-- 
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>내용</th>
            <th>조회수</th>
        </tr>
        -->
    </thead>
    <tbody>
        <tr>
            <td>글번호</td>
            <td>${bvo.bno}</td>
        </tr>
        <tr>
            <td>제목</td>
            <td>${bvo.title}</td>
        </tr>
        <tr>
            <td>작성자</td>
            <td>${bvo.writer}</td>
        </tr>
        <tr>
            <td>등록일</td>
            <td>${bvo.regAt}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>${bvo.content}</td>
        </tr>
        <tr>
            <td>조회수</td>
            <td>${bvo.readCount}</td>
        </tr>
    </tbody>
    

    
</table>
    <!-- file 표시 영역 231016 -->



<a href="/board/modify?bno=${bvo.bno}"><button class="btn btn-primary">글 수정</button></a> <br>

<a href="/board/remove?bno=${bvo.bno}"><button   class="btn btn-danger">글 삭제</button></a> <br>

<a href="/board/list/"><button class="btn btn-secondary">글 리스트</button></a>
<br>
<br>
<!-- 231020댓글라인S -->
<div class= "container" >
	<!--댓글등록라인S -->
	<div class="input-group mb-3">
	  <span class="input-group-text" id="cmtWriter">Writer</span>
	  <input type="text" class="form-control" placeholder="Test Comment Content" id = "cmtText">
	  <button type="button" class="btn btn-success" id="cmtPostBtn">POST(댓글등록)</button>
	</div>
	<!--댓글등록라인E -->
	
	<!-- 댓글 표시 라인S -->
	<ul class="list-group list-group-flush" id="cmtListArea">
	  <li class="list-group-item">
	  	<div class = "mb-3">
	  		<div class= "fw-bold">Writer</div>
	  		Content
	  	</div>
	  	<span class="badge rounded-pill text-bg-dark">modAt</span>
	  </li>
	  
	</ul>
	<!-- 댓글 표시 라인E -->
	
	<!-- 댓글 페이징 라인S -->
	<div>
		<div>
			<button type="button" id="moreBtn" data-page="1"
			class="btn btn-outline-dark" style="visibility:hidden">MORE+</button>
		</div>
	</div>
	<!-- 댓글 페이징 라인E -->
	
	
	<!-- 모달창S -->
	<div class="modal" id="myModal" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	    
	      <div class="modal-header">
	        <h5 class="modal-title">Writer</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      
	      <div class="modal-body">
      		<div class="input-group mb-3">
			<input type="text" class="form-control" placeholder="Te1st Comment Content" id = "cmtTextMod">
	  		<button type="button" class="btn btn-success" id="cmtModBtn">POST</button>
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary btn_close" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 모달창E -->
	
	
</div>
<!-- 231020댓글E -->


<script type="text/javascript">
let bnoVal=`<c:out value="${bvo.bno}"/>`; //이래야 js에서 도 쓰고 여기서도 if문등으로 쓸수 있음 처음엔 $로 받음
console.log(bnoVal);
</script>

<script type="text/javascript" src="/resources/js/boardComment.js">
</script>
<script>
getCommentList(bnoVal);
</script>



<jsp:include page = "../common/footer.jsp"/>
</body>
</html>