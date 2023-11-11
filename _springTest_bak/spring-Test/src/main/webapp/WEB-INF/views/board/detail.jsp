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

<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set value="${boardDTO.bvo}" var="bvo"></c:set> <!-- bvo을 넣어줄꼐요?231016 -->
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
            <td>${bvo.registerDate}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>${bvo.content}</td>
        </tr>
        <tr>
            <td>조회수</td>
            <td>${bvo.read_count}</td>
        </tr>
    </tbody>
    

    
</table>
    <!-- file 표시 영역 231016 -->
    <c:set value="${boardDTO.flist}" var="flist"></c:set>
  <div>
    	<ul>
    		<!-- 파일 개수 만큼 li를 추가하여 파일을 표시 타입이 1일 경우만 표시(그림만 나타내기) -->
    		<!-- li
    			div => img 그림표시
    			div => div 파일이름, 작성일자 span 크기 설정  			 -->
    		<!-- 하나의 파일로 따와서 fvo로 저장 -->
    		<c:forEach items="${flist}" var="fvo">
    			<li>
    				<c:choose>
    					<c:when test = "${fvo.file_type > 0 }">
    						<div>
				               <!-- /upload/year/month/dat/uuid_th_filename --><!-- 이거 1줄로 써야함 엔터치면 인식못함 -->
				                  <img alt="그림없음" src="/upload/${fn: replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_th_${fvo.file_name}">
    						</div>
    					</c:when>
    					<c:otherwise>
    						<div>
    							<!-- 없으면 file 아이콘 같은 모양값으로 넣을 수 있음. -->
    							
    						</div>
    					</c:otherwise>
    				</c:choose>
    				<div>
    					<div>
    						<div>${fvo.file_name}</div>
    						${fvo.reg_date}
    					</div>
    					<span>${fvo.file_size}Byte</span>
    				</div>
    			</li>
    		</c:forEach>
    	</ul>
    </div>


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

<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>