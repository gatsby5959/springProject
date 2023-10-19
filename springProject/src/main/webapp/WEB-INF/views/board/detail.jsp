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



<a href="/board/modify?bno=${bvo.bno}"><button>수정</button></a> <br>

<a href="/board/remove?bno=${bvo.bno}"><button>삭제</button></a> <br>

<a href="/board/list/"><button>리스트</button></a>

<br>
<br>

<!-- 댓글 라인S -->
<div>
	<!-- 댓글 작성 라인 -->
	<div>
		<span id="cmtWriter">${ses.id }</span>
		<input type="text" id="cmtText" placeholder="Add Comment...">
		<button type="button" id = "cmtPostBtn">댓글등록</button>
	</div>
	
	<!-- 댓글 표시 라인 -->
	<div>
		<ul id="cmtListArea">
			<li>
				<div>
					<div>Writer</div>
					Content
				</div>
				<span>reg_date</span>
			</li>
		</ul>
	</div>
</div>
<!-- 댓글 라인E -->

<script type="text/javascript">
const bnoVal = `<c:out value = "${bvo.bno}"/>`;
const writerVal = `<c:out value = "${bvo.writer}"/>`;
// const commentwriterVal = `<c:out value = "${cvo.writer}"/>`;
const sesId = `<c:out value = "${ses.id}"/>`; //js로 보내버림
console.log("bnoVal은 "+bnoVal+" /  sesId는 "+sesId);
</script>
<script type="text/javascript" src="/resources/js/boardComment.js"></script><!-- 해당위치에 넣어주기 -->
<script>
getCommentList(bnoVal);
</script>



<jsp:include page = "../common/footer.jsp"/>
</body>
</html>