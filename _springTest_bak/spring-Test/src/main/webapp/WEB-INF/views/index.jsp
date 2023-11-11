<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="./layout/header.jsp"></jsp:include>


<h1>
	Hello world!  
</h1>

<P>  My Spring Project 마이 스프링 프로젝트 </P>

<%-- <c:set var="name" value="홍길동" /> --%>
<c:if test="${ses.id ne null}">
    <p>접속중인 아이디-->${ses.id} ( ${ses.email} ) welcome~!!</p>  
<%--     <div>접속중인 아이디의 이메일-->${ses.email}</div>   --%>
</c:if>

<c:if test="${ses.id eq null}">
    <div>접속중인 아이디 없음</div>  
</c:if>



<script>
const msg_login = `<c:out value="${msg_login}" />`;
const msg_logout =  `<c:out value="${msg_logout}" />`;
const msg_modify =  `<c:out value="${msg_modify}" />`;

console.log(msg_login);
console.log(msg_logout);

if(msg_login == 1){
	alert("로그인 실패");
}
if(msg_logout == 1){
	alert("로그아웃 되었습니다.");
}
if(msg_modify == 2){
	alert("수정되고 로그아웃되었습니다.");
}
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>
