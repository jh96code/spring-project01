<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.example.Changwon.DTO.goodsDto" %>
<%@ page import="com.example.Changwon.DTO.cartDto" %>
<%@ page import="com.example.Changwon.DAO.goodsDao" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<meta charset="UTF-8">
<title>장바구니</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
   function cartdel() {
      if (confirm("장바구니의 상품을 모두 삭제하시겠습니까?")) {
         location.href= "cartdel";
      }
   }
   function selectdel() {
      if (confirm("선택하신 상품을 삭제하시겠습니까?")){
         document.cartForm.action = "seldel";
         document.cartForm.submit();
      }
   }
</script>
</head>
<body>
   <jsp:include page="/WEB-INF/view/header.jsp"/>
   <main>
      <div class = "container">
         <br><br>
         <h1>장바구니</h1>
         <hr><br>
         <div class = "row">
            <table width = "100%">
               <tr>
               <%
                		String loginid = (String) session.getAttribute("loginid");			
				%>
                  <td align = "left"><input type="button" value="전체 삭제" class="btn btn-danger" onclick="cartdel()"></td>
                  <%
                 	ArrayList<cartDto> cartList = (ArrayList<cartDto>)session.getAttribute("myCart");
                  	if(cartList== null){
                  %>
                  <td align = "right"><a href="goodslist" class = "btn btn-success" >상품보기</a></td>
                  <%} 
                  	else{
                  %>                  	
                  <td align = "right"><a href="orderinfo?id=<%=loginid%>" class = "btn btn-success" >주문하기</a></td>
                  <%} %>
               </tr>
            </table>
         </div>
         <div style = "padding-top:50px">
            <table id="tbl" class = "table table-hover">
               <tr>
                  <th>상품</th>
                  <th>가격</th>
                  <th>수량</th>
                  <th>소계</th>
                  <th>비고</th>
               </tr>
               
               <%   
                  int sum = 0;
                  
                  System.out.println("addcart.jsp페이지"+cartList);
                  if (cartList == null){
                     cartList = (ArrayList<cartDto>)new ArrayList<cartDto>();
                  }
                  for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
                     cartDto goods = cartList.get(i);
                     int total = goods.getCartPrice() * goods.getCartStock();
                     sum = sum + total;
               %>
               
               <tr>
                  <td><%=goods.getCartName()%> </td>
                  <td><fmt:formatNumber value="<%=goods.getCartPrice()%>"/>원 </td>
                  <td><%=goods.getCartStock() %> </td>
                  <td><fmt:formatNumber value="<%=goods.getCartPrice()* goods.getCartStock()%>"/>원</td>
                  <td>
                  <form name="cartForm" action = "seldel" method = "post">
                        <input type="hidden" name="seldelId" value="<%=goods.getCartId()%>">
                        <input type="submit" class="btn btn-danger" value="삭제" onclick="selectdel()"> 
                     </form>
                  </td>
               </tr>
               <%
                     } 
               %>
               <tr>      
                  <th></th>
                  <th></th>
                  <th>총액</th>
                  <th><fmt:formatNumber value="<%=sum %>"/>원</th>
                  <th></th>
               </tr>
            </table>
            <a href="goodslist" class="btn btn-secondary">&laquo;쇼핑 계속하기</a>
         </div>
         <hr>
      </div>
   </main>
   <jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>

</html>