<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp입니다.</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<h1>Board Detail Page</h1>
	<%-- - 컨트롤러 detail case에서 bvo를 셋 request에 이미 해주었음 그걸 여기서 이렇게 뽑아서 씀 ${bvo.image_File ${bvo.bno} --%>
	<c:if test="${bvo.image_File ne '' && bvo.image_File ne null}">
		<div>
			<img src="/_fileUpload/${bvo.image_File}" alt="NO Image!!">
		</div>
	</c:if>
	
	<table border=1 class="table table-dark">
		<tr>
			<th>BNO</th>
			<th>${bvo.bno}</th> 
		</tr>
		<tr>
			<th>TITLE</th>
			<th>${bvo.title}</th>
		</tr>
		<tr>
			<th>WRITER</th>
			<th>${bvo.writer}</th>
		</tr>
		<tr>
			<th>CONTENT</th>
			<th>${bvo.content}</th>
		</tr>
		<tr>
			<th>REGDATE</th>
			<th>${bvo.regdate}</th>
		</tr>
		<tr>
			<th>MODDATE</th>
			<th>${bvo.moddate}</th>
		</tr>		
	</table>
	
	<%--아이디가 맞으면 보이게 처리--%>
	<c:if test="${ses.id eq bvo.writer}">
		<a href="/brd/modify?bno=${bvo.bno}"><button class="btn btn-outline-success">수정버튼</button></a>
		<a href="/brd/remove?bno=${bvo.bno}"><button class="btn btn-outline-secondary">삭제버튼</button></a>
	</c:if>	
	
	<a href="/brd/pageList"><button type="button">list</button></a>
	
	<hr>
	
	<!-- 댓글 등록 라인 -->
	<div>
		Comment Line<br>
		<input type="text" id="cmtWriter" value="${ses.id}" readonly="readonly"><br>
		<input type="text" id="cmtText" placeholder="Add Comment"><br>
		<button type=button id = "cmtAddBtn">댓글 등록</button>
	</div>
	
	<br>
	
	<!-- 댓글표시라인S -->  <!-- https://getbootstrap.com/docs/5.3/components/accordion/ --> <!-- https://getbootstrap.com/docs/5.3/getting-started/download/ -->
	<div class="accordion" id="accordionExample">
	<!-- 댓글 아이템 1개 -->
		<div class="accordion-item">
			<h2 class="accordion-header">
				<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					cno, writer, regdate
				</button>
			</h2>
		
			<div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
				<div class="accordion-body">
        		content
      			</div>
			</div>
		</div>
	</div>
	<!-- 댓글표시라인E -->

<script type="text/javascript">
const bnoVal = `<c:out value="${bvo.bno}"/>`;
</script>

<script src="/resources/board_detail.js"></script>

</body>

<script type="text/javascript">
printCommentList(bnoVal);<%--바로 댓글 뿌려줌 --%><%--JS에 있음--%><%--정확히는 이 안의 함수인 spreadCommentList(result);가  뿌려주는듯 --%>
</script>


</html>










