<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원정보 수정</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>


<!-- <h1>회원정보 수정페이지</h1> -->
<!-- <form action = "/mem/update" method="post"> -->
<%-- ID:<input type="text" name="id" value="${ses.id }" readonly="readonly"><br> --%>
<%-- PassWord:<input type="text" name="pwd" value="${ses.pwd }"><br> --%>
<%-- Email:<input type="text" name ="email" value="${ses.email }"><br> --%>
<%-- Age:<input type="text" name ="age" value ="${ses.age }"><br> --%>
<!-- <button type="submit">수정</button> -->
<!-- </form> -->
<!-- <a href="/mem/remove"><button type="button">회원탈퇴</button></a> -->









   <div class="container bg-gradient-primary">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">회원정보 수정</h1>
                            </div>
                            <form class="user" action = "/mem/update" method="post">
							    <div class="form-group row">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">아이디</label>
							        <div class="col-sm-10">
<%-- 							        <input type="text" name="id" value="${ses.id }" readonly="readonly"> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="First Name"  name="id" value="${ses.id }" readonly="readonly">
							        </div>
							    </div>
							    <div class="form-group row">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">암호</label>
							        <div class="col-sm-10">
<%-- 							        <input type="text" name="pwd" value="${ses.pwd }"> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="First Name" name="pwd" value="${ses.pwd }">
							        </div>
							    </div>
							    <div class="form-group row">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">Email</label>
							        <div class="col-sm-10">
<%-- 							        <input type="text" name ="email" value="${ses.email }"> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="First Name" name ="email" value="${ses.email }">
							        </div>
							    </div>
							    <div class="form-group row">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">Age</label>
							        <div class="col-sm-10">
<%-- 							        <input type="text" name ="age" value ="${ses.age}">							         --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="First Name" name ="age" value ="${ses.age }">
							        </div>
							    </div>
                                   

<!--                                 <a href="/mem/update" class="btn btn-primary btn-user btn-block" method="post"> -->
<!--                                     수정 완료 -->
<!--                                 </a> -->
<!--                                 <hr> -->
<!--                                 <a href="index.html" class="btn btn-google btn-user btn-block"> -->
<!--                                     <i class="fab fa-google fa-fw"></i> Register with Google -->
<!--                                 </a> -->
<!--                                 <a href="index.html" class="btn btn-facebook btn-user btn-block"> -->
<!--                                     <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook -->
<!--                                 </a> -->

								<button type="submit" class="btn btn-primary btn-user btn-block">수정</button>
								
								<a href="/mem/remove"  class="btn btn-facebook btn-user btn-block">회원탈퇴</a>
								
                            </form>
                            
                           
                            
                            <hr>
                            <div class="text-center">
                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="login.html">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>














</body>
</html>