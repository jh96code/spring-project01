<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/style.css" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class = "jumbotron">
		<div class = "container">
			<h1 class = "display-3">주문 완료</h1>
			<br>
		</div>
	</div>
	
	<div class="container">
		<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
		<p> 주문은 2~3일 내에 배송될 예정입니다!

	</div>
	<div class="container">
		<p><a href="goodslist" class="btn btn-secondary">&laquo; 상품 목록</a>
	</div>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>