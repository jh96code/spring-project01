<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의 목록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
		<div class="container w-75 mt-5 mx-auto">
			<h2>문의 보내기</h2>
			<hr>
			<form action="cssendProcess" method="post" name="cssendForm">
				<div class="col-12 row">
					<label class="col-1 control-label">제목:</label>
					<input type="text" class="col-11" name="title" placeholder="제목을 입력하세요.">
				</div> <br>
				<div class="col-12 row">
					<label class="col-1 control-label">아이디:</label>
					<input type="text" class="col-4" name="member_id" value="${loginid}" readonly>
				</div> <br>
				<div class="col-12 row"> 
					<label class="col-1 control-label">문의 유형:</label>
					<select name="category" class="col-4">
						<option value="default">===문의 유형===</option>
						<option value="회원 관련 문의">회원 관련 문의</option>
						<option value="홈페이지 관련 문의">홈페이지 관련 문의</option>
						<option value="기타 문의">기타 문의</option>
					</select> 
				</div> <br>
				<div class="col-12 row">
					<label class="col-1 control-label">내용:</label>
					<textarea name="content"class="col-11 "  style="resize: none;" placeholder="내용을 입력하세요."></textarea>
				</div> <br>
				<hr> <br>
				<div class="d-flex justify-content-between">
					<a href="javascript:history.back()" class="btn btn-primary">문의 목록</a>
					<input type="button" value="문의 보내기" class="btn btn-primary" onclick="return checkForm()">
				</div>
			</form>
		</div>
		<br>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
	<script type="text/javascript">
	function checkForm() {
		if (!document.cssendForm.title.value) {
			alert("제목을 입력하세요.");
			return false;
		}
		if (document.cssendForm.category.value == "default") {
			alert("문의 유형을 선택하세요.");
			return false;
		}
		if (!document.cssendForm.content.value) {
			alert("내용을 입력하세요.");
			return false;
		}
		if (confirm("문의내용을 등록 하겠습니까?")) {
	        alert("문의하기 등록 성공!");
	        document.cssendForm.submit();
	    }
    }
	</script>
</body>
</html>