<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
	<title>게시판 수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
	    <div class="container w-75 mt-5 mx-auto">
		    <h1>게시판 수정</h1>
		    <div class="d-flex justify-content-between">
		    	<div>아이디: ${board.id}</div>
		    	<div>작성날짜: ${board.bdate}</div>
		    </div>
		    <hr>
		    <div>
				<form action="boardupdateProcess" method="post" name="BoardUpdateForm" enctype="multipart/form-data">
					<input type="hidden"name="num" value="${board.num}">
					<div class="col-12 row">
						<label class="col-1 control-label">제목:</label>
						<input type="text" class="col-11" name="title" value="${board.title}">
					</div> <br>
					<div class="col-12 row">
						<label class="col-1 control-label">이미지:</label>
						<input type="file" class="col-2" name="file">
					</div> <br>
					<div class="col-12 row">
					<label class="col-1 control-label">내용:</label>
						<textarea name="content"class="col-11 "  style="resize: none;">${board.content}</textarea>
					</div> <br>
				</form>
			</div>
			<hr>
			<div class="d-flex justify-content-end col-12">조회수: ${board.hit}</div>
			<br>
		    <div class="d-flex justify-content-between">
		    	<a href="javascript:history.back()" class="btn btn-primary">뒤로가기</a>
		    	<input type="button" value="수정하기" class="btn btn-primary" onclick="boardUpdate()">
		    </div>
	    </div>
	    <br>
	</main>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function boardUpdate() {
        if (!confirm("게시판을 수정하시겠습니까?")) {
        } else {
            alert("게시판 수정 완료!");
            document.BoardUpdateForm.submit();
        }
    }
</script>
</html>