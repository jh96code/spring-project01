<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.Changwon.DTO.memberDTO"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
	<title>회원정보 수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class="container">
		<br><br>
		<h1>마이페이지</h1>
		<hr>
		<br>
			<div class="form-group row d-flex justify-content-between">
			<a href="myorderlist?id=${member.id}"></a>
			<a href="myorderlist?id=${member.id}" class="btn btn-success">구매 내역</a>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 " for="id">아이디</label>
				<div class="col-sm-3">
					<div class="form-control">${member.id}</div>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2" for="name">성명</label>
				<div class="col-sm-3">
					<div class="form-control">${member.name}</div>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성별</label>
				<div class="col-sm-3">
					<div class="form-control">${member.gender}</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2" for="birth">생일</label>
				<div class="col-sm-3">
					<div class="form-control">${member.birth}</div>
				</div>
			</div>
			<div class="form-group  row ">
				<label class="col-sm-2" for="mail">이메일</label>
				<div class="col-sm-3">
					<div class="form-control">${member.mail}</div>
				</div>			
			</div>
			<div class="form-group  row">
				<label class="col-sm-2" for="phone">전화번호</label>
				<div class="col-sm-3">
					<div class="form-control">${member.phone}</div>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 " for="address">주소</label>
				<div class="col-sm-5">
					<div class="form-control">${member.address}</div>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 " for="address">가입날짜</label>
				<div class="col-sm-5">
					<div class="form-control">${member.regist_day}</div>
				</div>
			</div>
			<hr>
			<br>
			<div class="form-group row d-flex justify-content-between">
				<a href="updatemember?id=${member.id}" class="btn btn-primary">수정</a>
				<form action="deletemember?id=${member.id}" method="post" name="delMember">
					<input class="btn btn-danger" onclick="DeleteMember()" value="회원탈퇴" type="button">
				</form>
			</div>
	</div>
	<br>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function DeleteMember() {
	    if (!confirm("회원탈퇴 하시겠습니까?")) {
	    } else {
	        alert("회원탈퇴 성공!");
	        document.delMember.submit();
	    }
	}
</script>
</html>