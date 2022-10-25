<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.Changwon.DTO.BoardDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>게시판</title>
	<%
    	ArrayList<BoardDto> boardList = (ArrayList<BoardDto>)request.getAttribute("boardlist");
    %>  
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
	    <div class="container w-75 mt-5 mx-auto">
	        <h1>게시판 목록</h1>
	        <hr>
	        <table class="col-12 table table-hover text-center">
	            <tbody>
	                <tr style="cursor:default">
	                    <th class="col-1">NO</th>
	                    <th class="col-6">제목</th>
	                    <th class="col-2">아이디</th>
	                    <th class="col-2">등록일</th>
	                    <th class="col-1">조회수</th>
	                </tr>
	                			<%
			if (boardList != null) {
							for(int i = 0; i < boardList.size(); i++) {
								BoardDto dto = boardList.get(i);
					%>
					<tr>
						<td><%=dto.getNum()%></td>
						<td><a href="boardview?num=<%=dto.getNum()%>"><%=dto.getTitle()%></a></td>
						<td><%=dto.getId() %></td>
						<td><%=dto.getBdate() %></td>
						<td><%=dto.getHit() %></td>
					</tr>
					<%
							}
						}
						else {
					%>
					<tr>
						<td colspan="5" class="text-center">등록된 공지사항이 없습니다.</td>
					</tr>
					<%
						}
					%>
	            </tbody>
	        </table>
	        
	        <div align="center">
					<c:set var="pageNum" value="${pageNum}" />
					<c:forEach var="i" begin="1" end="${total_page}">
						<a href="<c:url value="/boardlist?pageNum=${i}" /> ">
							<c:choose>
								<c:when test="${pageNum==i}">
									<b> [${i}]</b>
								</c:when>
								<c:otherwise>[${i}]</c:otherwise>
							</c:choose>
						</a>
					</c:forEach>
				</div>
	        <br>
	        <div class="d-flex justify-content-between">
	        	<form action="boardlist" method="post">
		        	<table>
						<tr>
							<td width="100%" align="left">&nbsp;&nbsp; 
							<select name="items" class="txt">
								<option value="title">제목</option>
								<option value="id">아이디</option>
							</select> 
							<input name="text" type="text" placeholder="입력하세요."/> 
							<input type="submit" id="btnAdd" class="btn btn-primary " value="검색 " />
							</td>
						</tr>
					</table>
				</form>
	        	<input type="button" value="게시판 작성" onclick="loginCheck()" class="btn btn-primary">
	        </div>
	    </div>
    </main>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function loginCheck() {	
		if (${loginid==null}) {
			alert("로그인 해주세요.");
			location.href = "login"
			return false;
		}
		location.href = "boardwrite"
	}
</script>
</html>