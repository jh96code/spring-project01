<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

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
			<h1>문의사항</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<c:forEach var="Qlist" items="${Qlist}" varStatus="status">
					<ul>
						<li>
						<h4>관광지 : ${Qlist.name}</h4>
						<h4>만족도 : ${Qlist.score}</h4>
						<h4>문의사항 : ${Qlist.questions}</h4>
						</li>
						<hr>
					</ul>
				</c:forEach>
			</div>
			</table>
			<a href="javascript:history.back()" class="btn btn-primary">Back</a> 
		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp" />
</body>
</html>