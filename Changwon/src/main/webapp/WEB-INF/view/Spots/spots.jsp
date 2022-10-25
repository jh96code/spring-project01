<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	int total_record = ((Integer)request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	
		System.out.print("request넘어옴 확인 : "+total_record);
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&display=swap"rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">

<title>Document</title>
</head>

<script type="text/javascript">
	function loginCheck() {	
		if (${loginid==null}) {
			alert("로그인 해주세요.");
			location.href ="login"
			return false;
		}
		location.href ="addspots"
	}
</script>

<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
		<div class="container w-75 mt-5 mx-auto">
			<h1>관광지 목록</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<c:forEach var="spots" items="${spotslist}" varStatus="status">
					<ul>
						<li><a href="spot${spots.aid}"> 
						<h4>이미지이름 : ${spots.img}</h4>
						<h4>관광지 : ${spots.name}</h4>
						</a></li>
						<hr>
					</ul>
				</c:forEach>
			</div>
			</table>
			<div align="center">
					<c:set var="pageNum" value="${pageNum}" />
					<c:forEach var="i" begin="1" end="${total_page}">
						<a href="<c:url value="/spotslists?pageNum=${i}" /> "> <c:choose>
								<c:when test="${pageNum==i}">
								<b> [${i}]</b>
								</c:when>
								<c:otherwise>[${i}]</c:otherwise>
							</c:choose>
						</a>
					</c:forEach> 
			<br> <br>
			<div id="search" class="d-flex justify-content-between">
				<form action="spotslists" method="post">
					<select class="search_text" name="item">
						<option value="name">관광지</option>
						<option value="location">위치</option>
					</select> <input type="search" class="searchbar" name="spotsearch">
					<input type="submit" value="검색" class="searchbutton">
				</form>
						<%
				boolean admincheck = false;

				if (session.getAttribute("admincheck") != null)
					admincheck = (boolean) session.getAttribute("admincheck");

				if (admincheck == true) {
				%>
				<ul class="rigthfix">
					<li><a onclick="loginCheck()" class="btn btn-primary">관광지추가</a>
				</ul>
				<%
				}
				%>
			</div>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp" />
</body>
</html>