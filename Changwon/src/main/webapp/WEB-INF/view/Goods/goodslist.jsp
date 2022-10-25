<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/style.css" />
<title>목록보기</title>
<style>
	.goodscenter{
   		display: flex;
    	flex-direction: column;
    	
	}
	.banner{
		padding : 50px 0;
	}
	.maincontainer{
		padding-top: 100px;
		padding-right:50px;
		padding-left:50px;
	}
	
	.goodslistul{
		display:flex;
		flex-wrap: wrap;
	}
	.goodslistli{
		display: flex;
    	align-items: center;
    	flex-direction: column;
    	padding : 15px;
	}
	.goodsimg{
			width : 250px;
	    	height: 250px;
	    	/*원으로 만듬*/
	    	border-radius: 50%;
		}
	.center{
		display:inline-block;
    	padding:0.35em 0.65em;
    	font-weight:700;
    	text-align:center;
	}
</style>
</head>
<body>
	
		<jsp:include page="/WEB-INF/view/header.jsp"/>
	
	
	<main>
	<div class="container w-75 mt-5 mx-auto">
		 <div class="goodscenter">
			<h1>굿즈 목록</h1>
			<hr>
			<ul class="goodslistul">
			<c:forEach var="list" items="${goodslist}" varStatus="status">
			  <li class="goodslistli">
				<img class = "goodsimg" src="resources/img/${list.gImg }">			
			  
			  <a href="goods${list.gId}" class="center">[${status.count}]<br> ${list.gName} <br> 
			  가격 : <fmt:formatNumber value="${list.gPrice}" />원</a>
			  <%
			 					boolean admincheck = false;
						 		if(session.getAttribute("admincheck")!=null)
					 			admincheck = (boolean)session.getAttribute("admincheck");
			 		
						 		if(admincheck!=false){
						 	%>
							<a href="goodsdelete${list.gId}"><span class="badge bg-secondary">삭제</span></a>
							<% } %>
			  </li>
			</c:forEach> 
		</ul>
			<br>
			<div class="d-flex justify-content-end col-12">
			 	<%
			 		boolean admincheck = false;
			 		if(session.getAttribute("admincheck")!=null)
			 			admincheck = (boolean)session.getAttribute("admincheck");
			 		
			 		if(admincheck!=false){
			 	%>

					<div class="d-flex justify-content-end col-12">
					<a href="goodsaddform" class="btn btn-primary ">굿즈 추가</a>
					</div>
				<% } %>
			</div>
		</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>