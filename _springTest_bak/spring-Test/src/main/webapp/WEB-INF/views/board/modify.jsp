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

<c:set value="#{boardDTO.bvo}" var = "bvo"></c:set> <!-- 231016추가 -->

  <form action = "/board/modify" method="post" enctype = "multipart/form-data" >
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
    			
    				
    				<div>${fvo.file_name}</div>
					<button type="button" class="file-x" data-uuid="${fvo.uuid}">X</button><!-- 비동기로 할꺼 -->
    				<div>${fvo.uuid}</div>
    			</li>
    		</c:forEach>
    	</ul>
    </div>
<!-- 파일표시라인 E -->
	
<!-- 파일 등록 라인 S -->
	file: <input type="file" id="file" name="files" multiple="multiple" style="display:none"><!-- multiple은 여러개 올릴수도 있게 하는 옵션 -->
	<button type="button" id="trigger">FileUpload</button><br>
	<div id="fileZone">
	</div>
	
<!-- 파일 등록 라인 E -->
	<button type="submit" id="regBtn">수정</button>
</form>

<a href="/board/remove?bno=${bvo.bno}"><button>삭제</button></a> <br>

<a href="/board/list/"><button>리스트</button></a>

<!-- <script> -->
<%-- // const uuid =  `<c:out value = "${fvo.uuid}"/>`; --%>
<!-- </script> -->
<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>