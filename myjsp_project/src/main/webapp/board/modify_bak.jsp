<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
</head>
<body>
<h1>여기는 수정 페이지입니다. 수정하기 직전이죠 버튼 누르면 수정됨</h1>
<!-- 현재는 jsp페이지니까 /brd/를 써서 버튼 클릭시 컨트롤러가 잡게 한다. edit 가 잡힐것임 -->
<form action = "/brd/edit" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>BNO</th>
			<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td>
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
		<tr>
			<th>IMAGE_FILE</th>
			<td>
				<input type="hidden" name="image_file" value="${bvo.image_File}"> 
				<input type="file" name="new_file" accept="image/png, image/jpg, image/gif">
			</td>
		</tr>	
	</table>
	<button type = submit>수정</button>
</form>
</body>
</html>