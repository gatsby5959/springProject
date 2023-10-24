<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<form action="/board/modify" method="post">
		<table class="table table-dark table-striped">
			<tr>
				<th>#</th>
				<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${bvo.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="3" cols="30" name="content">${bvo.content }</textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${bvo.regAt }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${bvo.modAt }</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-info">수정</button>
	</form>
	
	<jsp:include page="../common/footer.jsp" />
</body>
</html>