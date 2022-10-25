<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>추가 폼</title>
</head>
<body>
   <jsp:include page="/WEB-INF/view/header.jsp"/>
   <main>
   		<div class="container w-75 mt-5 mx-auto">
			<h1>상품 현황</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<c:forEach var="list" items="${goodslist}" varStatus="status">
					<ul>
						<li> 
						<br>
						<h4> 상품 이름 : ${list.gName}</h4>
						<h4> 상품 가격 : ${list.gPrice}</h4>
						<h4> 상품 재고 : ${list.gStock}</h4>
						<br>
						</li>
						<hr>
					</ul>
				</c:forEach>
			</div>
			</table>
		</div>
	    <div class="container w-75 mt-5 mx-auto">
	    	<h1>굿즈 추가</h1>
	    	<hr>
			<form name="goodsadd" method="post" action="goodsadd" enctype="multipart/form-data">
				<label class="form-label">상품명</label>
				<input type="text" name="gName" class="form-control">
				<br>
				<label class="form-label">상품 가격</label>
				<input type="text" name="gPrice" class="form-control">
				<br>
				<label class="form-label">카테고리</label>
				<input type="text" name="gCategory" class="form-control">
				<br>
				<label class="form-label">재고</label>
				<input type="text" name="gStock" class="form-control">
				<br>
				<label class="form-label">상품 이미지</label>
				<input type="file" name="file" class="form-control">
				<br>
				<label class="form-label">상품 설명</label>
				<textarea cols="50" rows="5" name="gDescription" class="form-control"></textarea>
				<br>
				<button type="submit" class="btn btn-primary">저장</button>
				<br>
			</form>
			<br>
		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>