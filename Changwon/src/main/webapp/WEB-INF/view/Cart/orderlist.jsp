<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
<title>주문 목록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
		<div class="container w-75 mt-5 mx-auto">
			<h1>주문 목록</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<c:forEach var="list" items="${orderlist}" varStatus="status">
					<ul>
						<li> 
						<br>
						<h4> 주문 날짜 : ${list.orderAt}</h4>
						<h4> 주문 수량 : ${list.getNum}</h4>
						<h4> 주문 상품 : ${list.buygoods}</h4>
						<br>
						</li>
						<hr>
					</ul>
				</c:forEach>
			</div>
			</table>

		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp" />
</body>
</html>