<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<h1>Board list page 게시글리스트페이지</h1>
<!-- serach구역 S -->
<div>
	<c:set value="${ph.pgvo.type}" var="typed"></c:set>
	<form action="/brd/pageList" method = "get">
		<div>
			<select name = "type">
				<option ${typed == null ? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't'?'selected':''}>title</option>
				<option value="w" ${typed eq 'w'?'selected':''}>writer</option>
				<option value="c" ${typed eq 'c'?'selected':''}>content</option>
				<option value="tw" ${typed eq 'tw'?'selected':''}>title+writer</option>
				<option value="tc" ${typed eq 'tc'?'selected':''}>title+content</option>
				<option value="twc" ${typed eq 'twc'?'selected':''}>title+writer+content</option>
			</select>
			<input type="text" name="keyword" value="${ph.pgvo.keyword}" placeholder="Search..."><!-- 이미 ph.pgvo 에서 .keyword .pageNo .qty등이 계산이 되어 있는듯 -->
			<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo}"> <!-- 컨트롤로에서 이미 ph를 set해줌 -->
			<input type="hidden" name="qty" value="${ph.pgvo.qty }"> <!-- 객체.객체.변수   개념같음 -->
			<button type="submit" class="btn btn-outline-success">검색</button>
		</div>
	</form>
</div>
<!-- serach구역 E -->
<br>

<table border="1" class="table table-dark">
	<tr>
		<th>BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REG_DATE</th>
		<th>조회수</th>
	</tr>
	<!-- 컨트롤러의 request.setAttribute("list",list); -->
	<c:forEach items="${list}" var="bvo"> <!-- 컨트롤러에서 이미 list를 셋해준듯 -->
		<tr>
			<td><a href="/brd/count?bno=${bvo.bno}">${bvo.bno}</a></td> <!-- /brd/count만들어야함 --> <!-- bno를 count에 겟으로 넘김 -->
			<td>
				<c:if test="${bvo.image_File ne '' && bvo.image_File ne null}">
					<img src="/_fileUpload/_th_${bvo.image_File}"><%--여기까지 썸네일 포함이미지? --%>
				</c:if>
				<a href="/brd/count?bno=${bvo.bno}">${bvo.title}</a>
			</td>
			<td>${bvo.writer}</td>
			<td>${bvo.regdate}</td>
			<td>${bvo.readcount}</td>
		</tr>
	</c:forEach>
</table>

<%-- 페이지네이션 표시구역S --%>
<div>
	<%--prev(이전페이지) ◁ | --%>
	<c:if test="${ph.prev}">          <!-- 바로앞페이지로 감 ◁ | -->
		<a href ="/brd/pageList?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">◁ |</a> 
	</c:if>
	
	<c:forEach begin="${ph.startPage}" end = "${ph.endPage}" var="i">
		<a href = "/brd/pageList?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
	</c:forEach>
	
	<c:if test= "${ph.next}"> <!-- 바로 음페이지로 감 | ▷ -->
		<a href="/brd/pageList?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">| ▷</a>
	</c:if>	
</div>
<%-- 페이지네이션 표시구역E --%>

<a href="/index.jsp"><button type="button" class="btn btn-outline-primary">index</button></a>
<a href="/brd/register"><button type="button" class="btn btn-outline-secondary">register</button></a>

</body>
</html>




