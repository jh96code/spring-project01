<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 중복확인</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container" align="center">
		<br>
		<h2 align="left">ID 중복확인</h2>
		<hr><br>
		<form action="processid_check" class="form-horizontal" method="post">
			<div class="form-group">
				<div class="col-sm-2">
					<input name="id" type="text" class="form-control w-50" placeholder="아이디를 입력하세요." >
				</div> <br>
				<input type="submit" value="검색" class="btn btn-success w-50">	
			</div>
		</form>
	</div>
</body>
</html>