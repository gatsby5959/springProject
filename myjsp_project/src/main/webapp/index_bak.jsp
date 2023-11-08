<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp입니다.</title>
</head>
<body>
	<h1>Index Page,  로그인페이지입니다.</h1>
	<c:if test="${ses.id eq null }"> <%--세션이 없으면 보여줘라 --%>
		<form action="/mem/login" method="post">
		ID : <input type="text" name="id">	
		PWD : <input type="text" name="pwd">
		<button type="submit">LOGIN</button> <%--action="/mem/login"  간다 --%>
		</form>
	</c:if>
	
	<br>
	<hr>
	
	<div>
		<c:if test="${ses.id ne null }"><%--세션이 있으면 보여줘라--%>
			${ses.id } login  하였습니다<br>
			계정 생성일 : ${ses.regdate }<br>
			마지막 접속 : ${ses.lastlogin }<br>
			
			<a href="/mem/modify"><button>회원정보수정</button></a>
			<a href="/mem/list"><button>회원리스트</button></a>
			<a href="/mem/logout"><button>(회원당사자)로그아웃</button></a>
			<a href="/brd/register"><button>게시판 글쓰기</button></a>
		</c:if>
	</div>
	
	<a href="/mem/join"><button>회원가입</button></a>
	<br>
	<a href="/brd/pageList"><button>게시판 리스트</button></a>
	
	<%--아랫부분 이해불가2 --%>
	<script type="text/javascript"> <%--MemberController의 case"login":에서 로그인이 안될 상황에서는 0을 셋 해줌 request.setAttribute("msg_login", 0); //맵형식, (키, 밸류) --%>
		const msg_login=`<c:out value="${msg_login}"/>`; <%--이해불가3 c cout은 출력한다는 뜻인데? --%>
		console.log(msg_login);
		if(msg_login === '0'){
			alert("로그인 정보가 일치하지 않습니다.")	
		}
	</script>
</body>
</html>