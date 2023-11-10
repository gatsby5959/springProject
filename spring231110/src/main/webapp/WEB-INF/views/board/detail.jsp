<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
<style type="text/css">
.bigBox {
	width: 1000px;
	margin-left: 250px;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<div class="bigBox">
		<table class="table">

			<tr>
				<th scope="col">BNO</th>
				<td>${bdto.bvo.bno }</td>
			</tr>
			<tr>
				<th scope="col">WRITER</th>
				<td>${bdto.bvo.writer }</td>
			</tr>
			<tr>
				<th scope="col">TITLE</th>
				<td>${bdto.bvo.title }</td>
			</tr>
			<tr>
				<th scope="col">CONTENT</th>
				<td>${bdto.bvo.content }</td>
			</tr>
			<tr>
				<th scope="col">REG_AT</th>
				<td>${bdto.bvo.regAt }</td>
			</tr>
		</table>
		
		<!-- 파일 표시란 -->
		<c:set value = "${bdto.flist}" var ="flist"></c:set><!-- 난 안쓰는 부분인듯 -->
		<c:forEach items="${bdto.flist}" var="fvo"> 
			<c:choose>
				<c:when test="${fvo.fileType > 0}">
					<div>
<%-- 						<a href="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_${fvo.fileName}"> --%>
						<a href="/upload/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}">
<%-- 							<img src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}"> --%>
							<img src="/upload/${fvo.saveDir}/${fvo.uuid}_th_${fvo.fileName}">
						</a> <!-- 원본파일명 규칙 th해봄 -->							
					</div>
				</c:when>
				<c:otherwise>
					<!-- 이미지 없은 경우 아이콘 표시 -->
				</c:otherwise>
			</c:choose>
			<div class="ms-2 me-auto">
				<div class="fw-bold">${fvo.fileName }</div>
				<span class="badge bg-secondary rounded-pull">${fvo.fileSize}Byte</span>
<%-- 				<a href="/board/fileremove?bno=${bvo.bno}><div>X</div></a>  --%>
			</div>
			
<!-- 			<dir> -->
<%-- 				file_name : ${fvo.fileName} <br> --%>
<%-- 				reg_date : ${fvo.regAt}<br> --%>
<%-- 				file_size : ${fvo.fileSize}  --%>
<!-- 			</dir>			 -->
		</c:forEach>

		<a href="/board/modify?bno=${bvo.bno}"><button	class="btn btn-primary" type="button">수정</button></a> 
		<a	href="/board/remove?bno=${bvo.bno}"><button	class="btn btn-primary" type="button" id = "memberBtn2">삭제</button></a> 
		<a	href="/board/list"><button class="btn btn-primary" type="button">리스트로</button></a>

		<hr>
		

		
		<!-- 댓글 라인 -->
		<div class="container">
			<!-- 댓글 등록 라인 -->
			<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.mvo.email" var = "authEmail"/>
			<div class="input-group mb-3">
				<span class="input-group-text" id="cmtWriter">${authEmail}</span> 
				<input
					type="text" class="form-control" id="cmtText"
					placeholder="Test Comment">
				
				<button class="btn btn-primary" id="cmtPostBtn" type="button">댓글등록</button>
			</sec:authorize>

				
			</div>


			<!-- 댓글 표시 라인 -->
			<ul class="list-group list-group-flush" id="cmtListArea">
			</ul>

			<!-- 댓글 더보기 버튼 -->
			<div>
				<div>
					<!-- style="visibility: hidden" <= 숨김 -->
					<button type="button" id="moreBtn" data-page="1"
						class="btn btn-outline-dark" style="visibility: hidden">MORE+</button>
				</div>
			</div>

			<!-- 모달창 -->
			<div class="modal" id="myModal" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Writer</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body">
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="cmtTextModal"
									placeholder="Test Comment">
								<button class="btn btn-primary" id="cmtModBtn" type="button">댓글수정</button>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">모달창닫기Close</button>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<script type="text/javascript">
			let bnoVal = `<c:out value="${bvo.bno}"/>`;
			console.log("bnoVal>>> " + bnoVal);
		</script>
		<script type="text/javascript" src="/resources/js/boardComment.js"></script>
		<script type="text/javascript">
			printCommentList(bnoVal);
		</script>
		
		
		
		<script type="text/javascript">
		// 페이지가 로드될 때 실행되는 함수
		window.onload = function() {
		  // Flash attribute로 전달된 메시지가 있는지 확인
		  var message = "<c:out value='${errorMessage}'/>";
		  if(message != null && message != "") {
		    alert(message); // 메시지가 있다면 alert으로 표시
		  }
		};
		</script>
		
		
	<%--황지영서포트 --%>	
	<script type="text/javascript">
		let isMod = `<c:out value="${isMod}"/>`;
		console.log(isMod);
		if (parseInt(isMod)) {
			alert("게시글 수정 성공");
		}

		bnoVal = `<c:out value="${bvo.bno}" />`;
		let authEmail = `<c:out value="${authEmail}" />`;
		let bvoWriter = `<c:out value="${bvo.writer}" />`; // 새로운 변수 bvoWriter를 정의

		console.log("황지영서포트로그 " + bnoVal + " / " + authEmail);
		
// 		let memberBtn = document.getElementById("memberBtn");
		/*글작성자와 로그인 email이 같을 경우*/
		let memberBtn2 = document.getElementById("memberBtn2");
		if (bvoWriter != authEmail) {
// 			memberBtn.style.display = "none";
			memberBtn2.style.display = "none";
		}
	</script>
		
		
		
		<jsp:include page="../common/footer.jsp" />
</body>
</html>