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
		<br>
		<h1>회원정보 수정</h1>
		<hr><br>
		<form class="form-horizontal"  action="processUpdatemember" method="post" name="updateMember">
			<div class="form-group  row">
				<label class="col-sm-2 " for="id">아이디</label>
				<div class="col-sm-3">
					<input name="id" id="id" type="text" class="form-control" value="${member.id}" readonly>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2" for="password">비밀번호</label>
				<div class="col-sm-3">
					<input name="password" id="password" type="text" class="form-control" value="${member.password}" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2" for="name">성명</label>
				<div class="col-sm-3">
					<input name="name" id="name" type="text" class="form-control" value="${member.name}" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성별</label>
				<div class="col-sm-10">
						<input name="gender" type="radio" value="남" checked/> 남
						<input name="gender" type="radio" value="여"/> 여
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2" for="birth">생일</label>
				<div class="col-sm-3  ">
					<input type="text" id="birth" name="birth" maxlength="30" class="form-control" value="${member.birth}"> 
				</div>
			</div>
			<div class="form-group  row ">
				<label class="col-sm-2" for="mail">이메일</label>
				<div class="col-sm-3">
					<input type="text" id="mail" name="mail" maxlength="100" class="form-control" value="${member.mail}"> 
				</div>				
			</div>
			<div class="form-group  row">
				<label class="col-sm-2" for="phone">전화번호</label>
				<div class="col-sm-3">
					<input name="phone" id="phone" type="text" class="form-control" value="${member.phone}" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 " for="address">주소</label>
				<div class="col-sm-5">
					<input name="address" id="address" type="text" class="form-control" value="${member.address}">
				</div>
			</div>
			<hr><br>
			<input type="button" class="btn btn-primary " value="수정" onclick="UpdateMember()"> 
			<a href="javascript:history.back()" class="btn btn-primary">취소</a>
		</form>
	</div>
	<br> 
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function UpdateMember() {
		if (confirm('회원정보를 수정하시겠습니까?')) {
			alert("회원정보 수정 완료!")
			document.updateMember.submit();	
		}
	}
</script>
</html>