<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./resources/css/style.css" />
<script type="text/javascript">
		function logoutbtn() {
			if (confirm('로그아웃하시겠습니까?')){
				alert("로그아웃 성공!");
				location.href="logout";
			}
		}
	</script>
<header>
	<%
		boolean admincheck = false;
		if (session.getAttribute("admincheck") != null)
			admincheck = (boolean) session.getAttribute("admincheck");
	%>
		<nav>
            <div class="flex">
                <ul class="left">
                    <li>
                        <a href="/Changwon">
                            <h2>Home</h2>
                        </a>
                    </li>
                    <li>
                        <a href="spotslists">관광지</a>
                    </li>
                    <li>ㅣ</li>
                    <li>
                        <a href="goodslist">굿즈</a>
                    </li>
                    <li>ㅣ</li>
                    <li>
                        <a href="boardlist">게시판</a>
                    </li>
                    <li>ㅣ</li>
                    <li>
                        <a href="searching">지도 검색</a>
                   </li>
                </ul>
               
                <ul class="right">
                	<%
                		String loginid = (String) session.getAttribute("loginid");
						if(loginid != null){
					%>	
						<li>
							<b><%=loginid%>님
								<%
									if (admincheck == true) {
								%><span>(관리자)</span><%} else{%>
								<span>(일반회원)</span><%}%>
							</b>
						</li>
						<li>
	                        <a href="mypage?id=<%=loginid%>" class=>마이페이지</a>
	                    </li>
	                    <li>ㅣ</li>
	                    <li>
                           <a href="goodscart?id=<%=loginid%>" class=>장바구니</a>
                       </li>
                       <li>ㅣ</li>
						<li>
	                        <a href="#" onclick="logoutbtn()">로그아웃</a>
	                    </li>
	                <%
						}else if(loginid == null){
	                %>
	                    <li>
	                        <a href="login">로그인</a>
	                    </li>
	                    <li>ㅣ</li>
	                    <li>
	                        <a href="addMember">회원가입</a>
	                    </li>
	               	<% 
                    	}
	                %>
                </ul>
            </div>
            <%
	        	if(loginid != null){
            %>
            	<a href="cslist?id=<%=loginid%>" class="cs">문의목록</a>
            <%} %>
        </nav> 
    </header>