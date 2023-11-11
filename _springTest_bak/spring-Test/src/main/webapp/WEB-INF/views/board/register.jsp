<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>

<jsp:include page="../layout/header.jsp"></jsp:include>
<h2> 게시글 등록 </h2>
<!-- mapping상태는 get / post가 별도의 mapping을 가짐(탐)  다르면 다른 매핑을 탐 -->

<form action="/board/register" method="post" enctype="multipart/form-data">
	title : <input type="text" name="title"><br>
	writer : ${ses.id} <input type="hidden" name="writer" value="${ses.id}" ><br>
	content : <textarea rows="5" cols="50" name = "content"></textarea><br>
	file: <input type="file" id="file" name="files" multiple="multiple" style="display:none"><!-- multiple은 여러개 올릴수도 있게 하는 옵션 -->
	<button type="button" id="trigger">FileUpload</button><br>
	<div id="fileZone">
	</div>
	<button type="submit" id="regBtn">등록</button>
</form>
<br>
<a href="/">
	<button type="button">홈</button>
</a>
<a href="/board/list">
	<button type="button">리스트</button>
</a>

<script type="text/javascript" src="/resources/js/boardRegister.js"></script><!-- 연결해주기 -->
<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>