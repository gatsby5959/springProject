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


<%-- <c:set value="#{boardDTO.bvo}" var = "bvo"></c:set> <!-- 231016추가 --> --%>
<c:set value="#{bvo}" var = "bvo"></c:set>
  <form action = "/board/modify" method="post" >
  <input type="hidden" name="bno" value="${bvo.bno}">
	<table>
		<tr>
			<th>BNO</th>
			<td>${bvo.bno}</td>
		</tr>
		<tr>
			<th>TITLE</th>
			<td><input type="text" name="title" value="${bvo.title}"></td>
		</tr>
		<tr>
			<th>WRITER</th>
			<td>${bvo.writer}</td>
		</tr>
		<tr>
			<th>CONTENT</th>
			<td><textarea row="4" cols="33" name="content">${bvo.content}</textarea></td>
		</tr>
	</table>
<!-- 파일표시라인 S -->



<!-- 파일표시라인 E -->
	
<!-- 파일 등록 라인 S -->

	
<!-- 파일 등록 라인 E -->
	<button type="submit" id="regBtn">수정</button>
</form>

<%-- <a href="/board/remove?bno=${bvo.bno}"><button>삭제</button></a> <br> --%>

<a href="/board/list/"><button>리스트</button></a>

<!-- <script> -->
<%-- // const uuid =  `<c:out value = "${fvo.uuid}"/>`; --%>
<!-- </script> -->
<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>


<jsp:include page = "../common/footer.jsp"/>
</body>
</html>