<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/static/css/style.css" />
	<meta charset="UTF-8">
	<title>로그인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class = "container" align="center">
		<br>
		<h1 align="left">로그인</h1>
		<hr><br>
		<div class ="col-md-4 col-me-offset-4">
			<h3 class = "form-signin-heading">Please sign in</h3>
			<br>
			<c:if test="${error != null}">
				<div class="alert alert-danger alert-dismissible fade show mt-3">
						${error}
				</div>
			</c:if>
			<form action = "logincheck" method = "post" name="loginForm">
				<div class = "form-group">
					<input type = "text" class="form-control" placeholder="ID" name='id' required autofocus>
				</div>
				<div>
					<input type="password" class="form-control" placeholder="Password" name='password' required>
				</div>
				<br>
				<button class="btn btn btn-lg btn-success btn-block" type="submit" onclick="login()">로그인</button>
			</form>
			<br>
			<a href="#" onclick="window.open('findId','new','resizable=0 width=600 height=400 left=600 top=300');return false">아이디찾기</a>ㅣ
			<a href="#" onclick="window.open('findPw','new','resizable=0 width=600 height=400 left=600 top=300');return false">비밀번호찾기</a>
		</div>
		<br><br><br><br>
	</div>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function login() {
		if (!document.loginForm.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!document.loginForm.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}
</script>
</html>