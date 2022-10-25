<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.Changwon.DTO.board_replyDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
   <title>게시판 상세보기</title>
   <%
    	ArrayList<board_replyDto> replyList = (ArrayList<board_replyDto>)request.getAttribute("replylist");
    	String loginid = (String) session.getAttribute("loginid");
        if(loginid == null){
           loginid = "";
        }
    %>
</head>
<body>
   <jsp:include page="/WEB-INF/view/header.jsp"/>
   <main>
       <div class="container w-75 mt-5 mx-auto">
          <h1>${board.title}</h1>
          <div class="d-flex justify-content-between">
             <div>작성자: ${board.id}</div>
             <div>작성날짜: ${board.bdate}</div>
          </div>
          <hr>
          <div class="card w-90 mx-auto">
             <div class="card-body">
                <p class="card-text">내용: ${board.content}</p>
                <c:if test="${board.img ne null}">
                	<img src="resources/img/${board.img}" style="max-width:250px; max-height: 250px;">
				</c:if>
             </div>
          </div>
          <!-- 게시판 댓글 시작 -->
          <br>
          <h4>댓글 목록</h4>
		    <div style="overflow:auto; max-height:150px;">
			    <table class="col-12 table table-hover text-center" >
		            <tbody>
		                <tr style="cursor:default">
		                    <th class="col-1">아이디</th>
		                    <th class="col-8">내용</th>
		                    <th class="col-2">등록일</th>
		                    <th class="col-1">삭제</th>
		                </tr>
						<%
							if (replyList != null) {
								for(int i = 0; i < replyList.size(); i++) {
									board_replyDto dto = replyList.get(i);
						%>
						<tr>
							<td><%=dto.getMember_id()%></td>
							<td><%=dto.getContent() %></td>
							<td><%=dto.getRegist_day() %></td>
								<%
									
									if(loginid.equals(dto.getMember_id())){
								%>
									<td>
										<b><a href="board_reply_delete?board_num=${board.num}&reply_num=<%=dto.getNum()%>" class="btn-danger" style="padding:0px 5px;">X</a></b>
									</td>
								<%  }%>
						</tr>
						<%
								}
							}
						%>
		            </tbody>
		        </table>
	        </div> <br>
	     	<form action="board_reply_write?board_num=${board.num}&member_id=${loginid}" method="post" class="d-flex justify-content-between col-12">
	     		<input type="text" class="col-11" name="content" placeholder="댓글 내용을 입력하세요.">
	     		<input type="submit" value="작성하기" class="btn btn-success" onclick="return ReplyWrite()">
	     	</form>
		    <!-- 게시판 댓글 끝 -->
          <br>
          <div class="d-flex justify-content-end col-12">조회수: ${board.hit}</div>
          <hr><br>
          <div class="d-flex justify-content-between">
             <a href="boardlist" class="btn btn-primary">게시판 목록</a>
             <div>
                <a href="boardupdate?num=${board.num}" class="btn btn-primary" onclick="return boardUpdate()">수정</a>
                <form action="boarddelete?num=${board.num}" method="post" name="delBoard" style="display:inline-block;">
                   <input class="btn btn-danger" onclick="return boardDelete()" value="삭제" type="button">
                </form>
             </div>
          </div>
       </div>
   </main> <br><br>
    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    <script type="text/javascript">
	   function boardDelete() {
	      if (${loginid==null}) {
	         alert("로그인 해주세요.");
	         location.href = "login"
	         return false;
	      }
	      if(${loginid != board.id}){
	         alert("권한이 없습니다.");
	         return false;
	      }
	      if (confirm('게시판을 삭제하시겠습니까?')) {
	         alert("게시판 삭제 성공!");
	         document.delBoard.submit();   
	      }
	   }
	   function boardUpdate() {   
	      if (${loginid==null}) {
	         alert("로그인 해주세요.");
	         location.href = "login"
	         return false;
	      }
	      if(${loginid != board.id}){
	         alert("권한이 없습니다.");
	         return false;
	      }
	   }
	   function ReplyWrite() {   
		  if (${loginid==null}) {
		     alert("로그인 해주세요.");
		     location.href = "login"
		     return false;
		  }
	   }
	</script>
</body>
</html>