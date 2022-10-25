<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/static/css/style.css" />
<title>목록보기</title>
<style>
	.goodscenter{
		display:flex;
		flex-direction: column
	}
	.banner{
		padding : 50px 0;
	}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
    <div class="goodscenter">
	    <img class = "banner" src="../resources/static/img/vacation-banner.png">
		<ul class="goodslistul">
			<c:forEach var="list" items="${goodslist}" varStatus="status">
			  <li class="goodslistli">
			  <img src="../resources/static/img/${list.gImg}" >
			  <a href="/goods/delete/${list.gId}" class="text-decoration-none">[${status.count}] ${list.gName}, ${list.gPrice}, ${list.gImg} 삭제 </a>
			  </li>
			</c:forEach> 
		</ul>
    </div>
    
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>