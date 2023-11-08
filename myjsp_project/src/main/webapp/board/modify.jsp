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

    <title>글 수정 페이지</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body>
<!-- <h1>여기는 수정 페이지입니다. 수정하기 직전이죠 버튼 누르면 수정됨</h1> -->
<!-- 현재는 jsp페이지니까 /brd/를 써서 버튼 클릭시 컨트롤러가 잡게 한다. edit 가 잡힐것임 -->
<!-- <form action = "/brd/edit" method="post" enctype="multipart/form-data"> -->
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>BNO</th> -->
<%-- 			<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>TITLE</th> -->
<%-- 			<td><input type="text" name="title" value="${bvo.title}"></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>WRITER</th> -->
<%-- 			<td>${bvo.writer}</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>CONTENT</th> -->
<%-- 			<td><textarea row="4" cols="33" name="content">${bvo.content}</textarea></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>IMAGE_FILE</th> -->
<!-- 			<td> -->
<%-- 				<input type="hidden" name="image_file" value="${bvo.image_File}">  --%>
<!-- 				<input type="file" name="new_file" accept="image/png, image/jpg, image/gif"> -->
<!-- 			</td> -->
<!-- 		</tr>	 -->
<!-- 	</table> -->
<!-- 	<button type = submit>수정</button> -->
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
                                <h1 class="h4 text-gray-900 mb-4">게시글 수정용 페이지</h1>
                            </div>
                            
<!--                             action = "/brd/edit" method="post" enctype="multipart/form-data" -->
                          	<form action="/brd/edit" method="post" enctype="multipart/form-data" >
							    <div class="form-group row">
							    
							    	<label for="exampleFirstName" class="col-sm-2 col-form-label">BNO:</label>
							        <div class="col-sm-10">
<%-- 							       <td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="title제목"  name="bno" value="${bvo.bno}" readonly="readonly">
							        </div>
							    
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">제목:</label>
							        <div class="col-sm-10">
<%-- 							       <td><input type="text" name="title" value="${bvo.title}"></td> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="title제목"  name="title" value="${bvo.title}">
							        </div>
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">작성자:</label>
							        <div class="col-sm-10">
<%-- 							      <td>${bvo.writer}</td> --%>
							            <input type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="writer작성자"  name="writer" value="${bvo.writer}" readonly="readonly">
							        </div>
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">글내용:</label>
							        <div class="col-sm-10">
<%-- 							     <td><textarea row="4" cols="33" name="content">${bvo.content}</textarea></td> --%>
							            <textarea type="text" class="form-control form-control-user" id="exampleFirstName"
							                placeholder="따듯한말한마디하기" rows="5" cols="60" name="content">
							                ${bvo.content}
							            </textarea>
							        </div>
							        <div class="col-sm-10">
							        <label for="exampleFirstName" class="col-sm-2 col-form-label">첨부파일:</label>
						<%-- 		<td>
							<input type="hidden" name="image_file" value="${bvo.image_File}"> 
							<input type="file" name="new_file" accept="image/png, image/jpg, image/gif">
						</td> --%>
							            <input type="hidden" name="image_file" value="${bvo.image_File}"> 
							            <input type="file" accept="image/png,image/jpg,image/jpeg,image/gif"
							              			 name="new_file">
							           
							        </div>
							        
							        
							    </div>					
								<button type="submit" class="btn btn-primary btn-user btn-block">글 수정</button>	
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