<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.example.Changwon.DTO.goodsDto" %>
<%@ page import="com.example.Changwon.DTO.cartDto" %>
<%@ page import="com.example.Changwon.DAO.goodsDao" %>
<%@ page import="com.example.Changwon.DTO.memberDTO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/style.css" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
	<div class = "jumbotron">
		<div class = "container">
		<br><br>
			<h1 >주문 정보</h1>
			<hr>
			<br>
		</div>
	</div>
	
	<div class="container col-8 alert alert-info">
		<div class="text-center">
			<h1>주문 목록</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				<a href="addresssel?id=${member.id }" class = "btn btn-success" >배송지 변경</a><br>
				
				<strong>배송주소 : ${member.address}</strong>  
				<br> 성명 : ${member.name}	
				<br>전화번호 : ${member.phone} 
				<br>
			
			</div>
			<div class="col-4" align="right">
				<p> <em>배송일 : 2~3일</em>
			</div>
		</div>
		<div>
		<form action="orderOK" method="post" name="orderForm">
			<table class="table table-hover">
				<tr>
					<th class="text-center">상품명</th>
					<th class="text-center">수량</th>
					<th class="text-center">가격</th>
					<th class="text-center">소계</th>
				</tr>
				<%
					int sum = 0;
					ArrayList<cartDto> cartList = (ArrayList<cartDto>)session.getAttribute("myCart");
					
					if (cartList ==null)
						cartList = new ArrayList<cartDto>();
					for (int i = 0;i<cartList.size(); i++){
						cartDto dto = cartList.get(i);
						int total = dto.getCartPrice()*dto.getCartStock();
						sum = sum +total;
				%>	
				<tr>
					<td class = "text-center"><em><%=dto.getCartName() %></em></td>
					<td class = "text-center"><%=dto.getCartStock()%></td>
					<td class = "text-center"><fmt:formatNumber value="<%=dto.getCartPrice()%>"/></td>
					<td class = "text-center"><fmt:formatNumber value="<%=total%>"/>원</td>
				</tr>	
				<%  
					} 
				%>	
				<tr>
					<td></td>
					<td></td>
					<td class="text-right"><strong>총액 : </strong></td>
					<td class="text-center text-danger"><strong><fmt:formatNumber value="<%=sum%>"/>원</strong></td>
				</tr>
				
			</table>
		
			<a href="goodscart"class="btn btn-secondary" role="button">이전</a>
			<a href="goodsorderOK?id=${member.id }" class="btn btn-success" role="button">주문 완료</a>
			<a href="goodslist"class="btn btn-secondary" role="button">취소</a>
		</form>	
		</div>
	</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
	
</body>
</html>