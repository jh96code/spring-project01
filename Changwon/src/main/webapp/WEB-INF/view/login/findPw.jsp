<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container" align="center">
		<br>
		<h1 align="left">비밀번호찾기</h1>
		<hr><br>
		<div class ="col-md-4 col-me-offset-4">
			<form action = "processfindPw" method ="post">
				<div class = "form-group">
					<div style="color:#999;">비밀번호 찾을 아이디를 입력해주세요.</div><br>
					<input type = "text" class="form-control" placeholder="아이디를 입력하세요." name='id' required autofocus>
					<br>
					<input class="btn btn-lg btn-success btn-block" type="submit" value="검색하기">
				</div>
			</form>
		</div>
	</div>
</body>
</html>