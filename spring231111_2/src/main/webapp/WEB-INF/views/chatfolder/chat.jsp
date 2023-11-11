<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ page session="false" %> 
<!-- <html> -->
<!-- <head> -->
<!-- 	<title>Index Page</title> -->
<!-- </head> -->
<!-- <body> -->
<%-- <jsp:include page="common/header.jsp"/> --%>
<%-- <jsp:include page="common/nav.jsp"/> --%>

<!-- <h3> -->
<!-- 메인입니다. -->
<!-- </h3> -->

<%-- <jsp:include page="common/footer.jsp"/> --%>
<!-- </body> -->
<!-- </html> -->
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>실시간 채팅 메신저 서비스</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value='../resources/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value='../resources/css/sb-admin-2.min.css'/>" rel="stylesheet" type="text/css">
    <link href="<c:url value='../resources/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
    <link href="<c:url value='../resources/css/custom.css'/>" rel="stylesheet" type="text/css">

	
</head>

<body id="page-top">
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>


<!-- 231111_1전경환 채팅창관련해서 뭐 넣기 S ------------------------------------->
<!-- https://www.youtube.com/watch?v=8s98IhtgwZ0&list=PLRx0vPvlEmdAlUbX_TGDxaSxKCvfl2isa&index=6 -->
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-xs-12">
            <div class="portlet portlet-default">
                <div class="portlet-heading">
                	<div class="portlet-title">
                    	<h4><i class="fa fa-circle text-green"></i>실시간 채팅창</h4>
                    </div>	
                	<div class="clearfix"></div>
            	</div>
	            <div id="chat" class="panel-collapse">
	                <div id="chatList" class="portlet-body chat widget" style="overflow-y: auto; width: auto; height:600px;">
	                </div>
	                <div class="portlet-footer">    
	                    <div class="row">
	                        <div class="form-group col-xs-4">
	                            <input style="height: 40px;" type="text" id="chatName" class="form-control" placeholder="이름" maxlength="8">
	                        </div>
	                    </div>
	                    <div class="row" style="height: 140px;">
	                        <div class="form-group col-xs-10">
	                            <textarea style="height: 80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100"></textarea>
	                        </div>
	                        <div class="form-group col-xs-2">
	                            <button id="chatSubmitBtn" type="button" class="btn btn-default pull-right" >전송</button>
	                            <div class="clearfix"></div>
	                        </div>
	                    </div>
	                </div>
	            </div>
           </div>
        </div>
    </div>
</div>
<div class="alert alert-success" id="successMessage" style="display: none;">
	<strong>메세지 전송에 성공했습니다.</strong>
</div>
<div class="alert alert-danger" id="dangerMessage" style="display: none;">
	<strong>이름과 내용을 모두 입력하세요</strong>
</div>
<div class="alert alert-warning" id="warningMessage" style="display: none;">
	<strong>데이터베이스 오류 발생</strong>
</div>



<!-- 231111_1전경환 채팅창관련해서 뭐 넣기 E ------------------------------------->





<script type="text/javascript" src="/resources/js/chat.js"></script>

<jsp:include page="../common/footer.jsp"/>
</body>

</html>
