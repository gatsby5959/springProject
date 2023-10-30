<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>	    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navBar</title>
<style type="text/css">
.boxZZZ{
display: flex;
justify-content: center;
width: 100%;
}
</style>
</head>
<body>
<header>
<nav>
<div class="boxZZZ" >
<div class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">	
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="/board/list"> BoardList!!</a>
        </li>
        
        ${principal }
        <%-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open  --%>
        <%-- 사용자 정보는 principal --%>
           <%-- admin 계정만 할 수 있는 일을 처리--%>
           <%-- admin 계정만 할 수 있는 일을 처리 --%>
        
        <%-- 버튼모양이 안이뻐서 아래처럼 해서 a링크를 누르면  id logoutform을 서브밋해서 보네 --%>
        <%-- 로그인 해야 open 되는 메뉴들... --%>
         <sec:authorize access="isAuthenticated()">
        
        <sec:authentication property="principal.mvo.email" var="authEmail"/>
        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
        <sec:authentication property="principal.mvo.authVOList" var="auths"/> <%--MemberVO의 private List<AuthVO> authVOList;에서의 값 --%>
        
        <c:choose>
           <c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
           <li class="nav-item">
           <a class="nav-link" href="/member/list">어드민> ${authNick }(${authEmail })</a>
           </li>
           </c:when>
           <c:otherwise>
           <li class="nav-item">
           		<a class="nav-link" href="/detail?email=${authEmail }">${authNick }(${authEmail })</a>
           </li>
           </c:otherwise>
        </c:choose>
           <li class="nav-item">
             <a class="nav-link" href="/board/register">Board Reg</a> <%-- 컨트롤러로~ --%>
           </li>
		<li class="nav-item">
		 	<a class="nav-link" href="/member/logout" id="logoutLink">LogOut</a>     
        </li>
	        <form action="/member/logout" method="post" id="logoutForm">
    	    	<input type="hidden" name="email" value="${authEmail}">
	         
        	</form>
        </sec:authorize>
        
        <%-- 썡 인증이 시도하지 않은 애들은 아래가 보임 비회원 --%>
        <sec:authorize access="isAnonymous()">
	        <li class="nav-item">
	          <a class="nav-link" href="/member/register">memberSignUp회원가입</a> 
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/member/login">memberLogIn</a> 
	        </li>
		</sec:authorize>
        
      </ul>
    </div>
  </div>
  </div>
  </div>
</nav>
</header>
<%-- 버튼모양이 안이뻐서 아래처럼 해서 a링크를 누르면  id logoutform을 서브밋해서 보네 --%>
<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click',(e)=>{
	e.preventDefault();
	document.getElementId('logoutForm').submit(); 
	})
}
</script>

</body>
</html>