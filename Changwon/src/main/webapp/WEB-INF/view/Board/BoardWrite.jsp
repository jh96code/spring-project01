<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	<title>게시판 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
		<div class="container w-75 mt-5 mx-auto">
			<h2>게시판 작성</h2>
			<hr>
			<div>
				<form action="boardwriteProcess" method="post" name="BoardWriteForm" enctype="multipart/form-data">
					<div class="col-12 row">
						<label class="col-1 control-label">제목:</label>
						<input type="text" class="col-11" name="title" placeholder="제목을 입력하세요.">
					</div> <br>
					<div class="col-12 row">
						<label class="col-1 control-label">아이디:</label>
						<input type="text" class="col-4" name="id" value="${loginid}" readonly>
					</div> <br>
					<div class="col-12 row">
						<label class="col-1 control-label">이미지:</label>
						<input type="file" class="col-4" name="file">
					</div> <br>
					<div class="col-12 row">
						<label class="col-1 control-label">내용:</label>
						<textarea name="content"class="col-11 "  style="resize: none;" placeholder="내용을 입력하세요."></textarea>
					</div> <br>
					<hr> <br>
					<div class="d-flex justify-content-between">
						<a href="javascript:history.back()" class="btn btn-primary">게시판 목록</a>
						<input type="button" value="작성하기" class="btn btn-primary" onclick="boardWrite()">
					</div>
				</form>
			</div>
		</div>
		<br>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function boardWrite() {
	    if (confirm("게시판을 작성하겠습니까?")) {
	        alert("게시판 작성 성공!");
	        document.BoardWriteForm.submit();
	    }
	}
</script>
</html>