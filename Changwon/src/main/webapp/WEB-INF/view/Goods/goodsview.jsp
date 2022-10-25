<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../resources/css/style.css" />
	<link rel="stylesheet" 	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<title>상세보기</title>
	<style>
		.goodscenter{
			display:flex;
			flex-direction: column
		}
		.goodview{
			display: flex;
	    	justify-content: space-around;
	    	align-items: center;
	    	flex-direction: column;
		}
		.goodsimg{
			width : 250px;
	    	height: 250px;
	    	/*원으로 만듬*/
	    	border-radius: 50%;
		}
		.goodsdsc{
			display: flex;
	    	justify-content: space-around;
	    	align-items: center;
	    	flex-direction: column;
		}
		.banner{
			padding : 50px 0;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<main>
	<div class = "container w-75 mt-5 mx-auto">
	<h1>상품 상세보기</h1>
	<hr>           
	    <div class="goodscenter">
	        <div class="goodview">
				<img class="goodsimg" src="resources/img/${goods.gImg}">
				<div class="goodsdsc">
				    <b>이름 : ${goods.gName }</b>
				    <b>가격 : <fmt:formatNumber value="${goods.gPrice }"/>원</b>
				    <p>재고 수 : ${goods.gStock }</p>
				    <p>상세설명 : ${goods.gDescription }</p>
				    <p>
				    <form name = "cartaddForm" action="goodsaddcart" method = "post">
						<input type="hidden" name = "gId" id = "gId" value=${goods.gId }>
						<input type="hidden" name = "gPrice" id = "gPrice"value=${goods.gPrice }>
						<input type="hidden" name = "gName" id = "gName"value=${goods.gName }>
						<input type="button" class = "btn btn-info" value="장바구니에 담기" onClick="addtocart()">
						<a href="goodscart" class = "btn btn-warning">장바구니 &raquo;</a>
						<a href="goodslist" class = "btn btn-secondary">상품 목록 &raquo;</a>
					</form>
				 </div>
	        </div> 
		</div>
	</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script type="text/javascript">
	function addtocart() {
		if(${goods.gStock } == 0){
			alert("품절입니다!!")
			return false
		}
	    if (confirm("장바구니에 추가하시겠습니까?")) {
	    	alert("장바구니 담기 성공!");
		    document.cartaddForm.submit();
	    }
	}
</script>
</html>