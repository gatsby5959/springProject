<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<form action="/board/register" method="post"><!-- 앞 board/ 삭제 해도 되고 /board/를 해도 됨-->
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">title</label>
	  <input type="text" class="form-control" name="title" id="exampleFormControlInput1" placeholder="title">
	</div>
	<div class="mb-3">
	  <label for="exampleFormControlTextarea1" class="form-label">writer</label>
	  <input type="text" class="form-control" name="writer" id="exampleFormControlTextarea1" rows="3"></textarea>
	</div>
		<div class="mb-3">
	  <label for="exampleFormControlTextarea1" class="form-label">Content</label>
	  <input class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
	</div>
	<button type="submit" class="btn btn-dark">등록</button>
</form>
<jsp:include page = "../common/footer.jsp"/>
</body>
</html>