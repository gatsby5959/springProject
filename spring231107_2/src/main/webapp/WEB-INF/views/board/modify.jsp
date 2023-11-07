<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"> -->
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th scope="col">BNO</th>
				<td><input type="number" name="bno" value="${bvo.bno}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">WRITER</th>
				<td><input type="text" name="writer" value="${bvo.writer}"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="col">TITLE</th>
				<td><input type="text" name="title" value="${bvo.title}"></td>
			</tr>
			<tr>
				<th scope="col">CONTENT</th>
				<td><textarea rows="3" cols="30" name="content">${bvo.content }</textarea>
				</td>
		</table>
		
		<!-- 새파일 등록 S-->
		<div class="mb-3">
			<!-- multiple="multiple" => 파일을 여러 개 등록하기 위해 -->
		  <input type="file" class="form-control"  name="files" id="files" style="display: none;" multiple="multiple"> <!-- name이 컨트롤러의 @RequestParam의 name임  -->
			<!-- input button trigger  용도의 button -->
		<button type="button" id="trigger" class="btn btn-outline-primary">File Upload</button>
		</div>
		<div class="mb-3" id="fileZone">
			<!-- 첨부파일 표시될 영역 -->
		</div>
		<!-- 새파일 등록 E-->
		
		
				<!-- 파일 표시란 ---------------------------------------------------------------S-->
		<c:set value = "${bdto.flist}" var ="flist"></c:set><!-- 난 안쓰는 부분인듯 -->
		<c:forEach items="${bdto.flist}" var="fvo"> <!-- bdto.삭제 전경환231025_00:40-->
			<li class="list-group-item d-flex justify-content-between align-items-start">  <!--class="list-groupitem d-flex justify   -->
			<c:choose>
				<c:when test="${fvo.fileType > 0}">
<!-- 					<div> -->
<%-- 						<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}"> <!-- 원본파일명 규칙 th해봄 --> --%>
<%-- 						<button type="button" class="file-x" data-uuid="${fvo.uuid }">X</button><!-- 비동기로 할꺼 --> --%>
<%--     					${fvo.uuid } --%>
<!-- 					</div> -->
				
					
				</c:when>
				<c:otherwise>
					<!-- 이미지 없은 경우 아이콘 표시 -->
				</c:otherwise>
			</c:choose>
			<div class="ms-2 me-auto">
				<div class="fw-bold">${fvo.fileName }</div>
				<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}">
				<span class="badge bg-secondary rounded-pull">${fvo.fileSize}Byte</span>
				<button type="button" data-uuid="${fvo.uuid}" class="btn btn-sm btn-danger py-0 file-x">X</button>
			</div>
			
<!-- 			<dir> -->
<%-- 				file_name : ${fvo.fileName} <br> --%>
<%-- 				reg_date : ${fvo.regAt}<br> --%>
<%-- 				file_size : ${fvo.fileSize}  --%>
<!-- 			</dir>			 -->
		
		</li>
		</c:forEach>
		<!-- 파일 표시란 ---------------------------------------------------------------E-->
		
		
		
		
		
		<button class="btn btn-primary" type="submit" id="regBtn">게시글수정완료</button>
		<a	href="/board/list"><button class="btn btn-primary" type="button">게시글리스트로</button></a>
	
	
	

	
	
		<!-- 파일등록라인2---------S -->

<!-- 	file: <input type="file" id="files" name="files" multiple="multiple" style="display:none">multiple은 여러개 올릴수도 있게 하는 옵션 -->
<!-- 	<button type="button" id="trigger">FileUpload</button><br> -->
<!-- 	<div id="fileZone"> -->
<!-- 	</div> -->
<!-- 	<button type="submit" id="regBtn">등록</button> -->

	<!-- 파일등록라인2---------S -->
	
	
	</form>

	
	
	
	
	
	
	<script type="text/javascript" src="/resources/js/boardModify.js"></script> <!-- 모디파이js동작?하라고 끼워줌 -->
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>