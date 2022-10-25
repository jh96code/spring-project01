<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
		<div class="container" align="center">
			<br>
			<h1 align="left">아이디찾기</h1>
			<hr><br>
			<div class ="col-md-4 col-me-offset-4">
				<form action = "processfindId" method ="post">
					<div class = "form-group">
						<div style="color:#999;">회원가입시 입력한 이름으로 아이디 찾기</div><br>
						<input type = "text" class="form-control" placeholder="이름을 입력하세요." name='name' required autofocus>
						<br>
						<input class="btn btn-lg btn-success btn-block" type="submit" value="검색하기">
					</div>
				</form>
			</div>
		</div>
</body>
</html>