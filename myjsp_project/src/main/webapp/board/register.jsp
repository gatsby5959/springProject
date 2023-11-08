<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>게시판 글쓰기</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
<!-- <h1>게시글 삽입용 인풋페이지 Board Register Page</h1> -->
<!-- <form action="/brd/insert" method="post" enctype="multipart/form-data"> -->
<!-- title제목:<input type="text" name="title"><br> -->
<%-- writer작성자:<input type="text" name="writer" value="${ses.id}" readonly="readonly"><br> --%>
<!-- content내용:<textarea rows="5" cols="60" name="content"></textarea><br> -->
<!-- image_file:<input type="file" name="image_file" accept="image/png, -->
<!-- image/jpg,image/jpeg,image/gif"><br> -->
<!-- <button type="submit">등록버튼</button> -->
<!-- </form> -->





 <div class="container bg-gradient-primary">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">게시글 삽입용 인풋페이지</h1>
                            </div>
                            
                          	<form action="/brd/insert" method="post" enctype="multipart/form-data" >
							    <div class="form-group row">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">제목:</label>
							        <div class="col-sm-10">
<%-- 							       title제목:<input type="text" name="title"><br> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="title제목"  name="title">
							        </div>
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">작성자:</label>
							        <div class="col-sm-10">
<%-- 							      writer작성자:<input type="text" name="writer" value="${ses.id}" readonly="readonly"><br> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="writer작성자"  name="writer" value="${ses.id}" readonly="readonly">
							        </div>
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">글내용:</label>
							        <div class="col-sm-10">
<%-- 							     content내용:<textarea rows="5" cols="60" name="content"></textarea><br> --%>
							            <textarea type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="따듯한말한마디하기" rows="5" cols="60" name="content"">
							            </textarea>
							        </div>
							        <div class="col-sm-10">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">첨부파일:</label>
<%-- 							     image_file:<input type="file" name="image_file" accept="image/png,image/jpg,image/jpeg,image/gif"><br> --%>
							            <input type="file" accept="image/png,image/jpg,image/jpeg,image/gif"
							              			 name="image_file">
							            </input>
							        </div>
							        
							        
							    </div>					
								<button type="submit" class="btn btn-primary btn-user btn-block">글 등록</button>	
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









</form>
</body>
</html>