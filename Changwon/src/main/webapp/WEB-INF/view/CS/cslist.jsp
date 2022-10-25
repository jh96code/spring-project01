<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>문의 목록</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%  
		String loginid = (String) session.getAttribute("loginid"); 
	%>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
		<div class="container w-75 mt-5 mx-auto">
                <h2>문의 목록<span style="font-size:19px;"> (회원용)</span></h2>
				<hr>
				<table class="col-12 table table-hover text-center">
		            <tbody>
		                <tr>
		                    <th class="col-2">아이디</th>
		                    <th class="col-5">제목</th>
		                    <th class="col-2">등록일</th>
		                    <th class="col-2">답변일</th>
		                    <th class="col-1"></th>
		                </tr>
		                <c:forEach var="cslist" items="${cslist}" varStatus="status">
		                	<tr class="cs" style="cursor:pointer;">
		                		<th>${cslist.member_id}</th>
			                    <th>${cslist.title}</th>
			                    <th>${cslist.send_day}</th>
			                    <th>
			                    	<c:choose>
			                    		<c:when test="${empty cslist.reply_day}">
				                    		답변 없음
				                    	</c:when>
				                    	<c:otherwise>
				                    		${cslist.reply_day}
				                    	</c:otherwise>
			                    	</c:choose>
			                    </th>
			                    <th>
			                    	<a href="deletecs?num=${cslist.num}&id=${loginid}" onclick="return deleteCS()" class="btn btn-danger" style="padding:0px 4px;">삭제</a>
			                    </th>
			                </tr>
			                <tr class="reply">
			                	<td colspan="5"> <br>
			                		<div class="col-12 row"> 
										<label class="col-1 control-label"><b>문의 유형:</b></label>
										<input class="col-4" value="${cslist.category}" readonly>
									</div> <br>
									<div class="col-12 row">
										<label class="col-1 control-label"><b>문의 내용:</b></label>
										<input class="col-10" value="${cslist.content}" readonly>
									</div> <br><hr><br>
									<c:choose>
										<c:when test="${empty cslist.reply_day}">
											<h3>아직 답변이 등록되지 않았습니다.</h3><br>
										</c:when>
										<c:otherwise>
											<div class="col-12 row">
												<label class="col-1 control-label" style="color:#c70000;"><b>답변자 아이디:</b></label>
												<input class="col-4" value="${cslist.reply_member_id}" readonly>
											</div> <br>
											<div class="col-12 row">
												<label class="col-1 control-label" style="color:#c70000;"><b>답변내용:</b></label>
												<input class="col-10" value="${cslist.reply_content}" readonly>
											</div> <br><br>
										</c:otherwise>
									</c:choose>
			                	</td>
			                </tr>
			           	</c:forEach>
	                </tbody>
	            </table>
	            <br> <hr> <br>
	            <div class="d-flex justify-content-end col-12">
	                <a href="cssend?id=<%=loginid%>" class="btn btn-primary">문의하기</a>
	            </div>
		</div>
		<br>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
		$(document).ready(function(){
			//문의 상세내용
			$('.cs').click(function(){
				if($(".reply").hasClass("view") === false){
					$(this).next($(".reply")).addClass("view");
				} else if($(this).next($(".reply")).hasClass("view") === true){
					$(this).next($(".reply")).removeClass("view");
				} else{
					$(".reply").removeClass("view");
					$(this).next($(".reply")).addClass("view");
				}
			})
		})
    </script>
</body>
</html>