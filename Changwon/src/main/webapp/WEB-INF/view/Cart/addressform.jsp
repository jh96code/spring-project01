<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>주소 추가</title>
</head>
<body>
   <jsp:include page="/WEB-INF/view/header.jsp"/>
   <main>
   		<div class="container w-75 mt-5 mx-auto">
			<h1>기본 배송지</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<ul>
					<li>
					<br>
					<h4> 기본 주소 : ${member.address}</h4>
					<h4> 기본 연락처 : ${member.phone} </h4>
					<h4> 기본 성명 : ${member.name}</h4>
					<br>
					</li>
				</ul>
			</div>
			</table>
		</div>
		<div class="container w-75 mt-5 mx-auto">
			<h1>추가 배송지</h1>
			<hr>
			 <table class="col-12 table table-hover text-center">
			<div>
				<c:forEach var="list" items="${addresslist }" varStatus="status">
					<ul>
						<li> 
						<br>
						<h4> 수령 주소 : ${list.addressAdr}</h4>
						<h4> 수령 연락처 : ${list.addressPhone} </h4>
						<h4> 수령 성명 : ${list.addressName}</h4>
						<br>
						<a href="selectaddress?adrn=${list.addressNum }" class="btn btn-primary ">배송지 선택</a>
						</li>
						<hr>
					</ul>
				</c:forEach>
			</div>
			</table>
		</div>
	    <div class="container w-75 mt-5 mx-auto">
	    	<h1>주소 추가</h1>
	    	<hr>
			<form name="addressform" method="post" action="addressform" enctype="multipart/form-data">
				<div class="form-group  row">
					<label class="col-sm-2 " for="id">아이디</label>
					<div class="col-sm-3">
						<input name="addressId" type="text" class="form-control" value="${member.id}" readonly>
					</div>
				</div>
				<div class="form-group  row">
					<label class="col-sm-2">수령인</label>
					<div class="col-sm-3">
					<input name="addressName" type="text" class="form-control" placeholder="Name" >
					</div>
				</div>
				<div class="form-group  row">
					<label class="col-sm-2">전화번호</label>
					<div class="col-sm-3">
						<input name="addressPhone" type="text" class="form-control" placeholder="Phone" >
					</div>
				</div>
				<div class="form-group  row">
					<label class="col-sm-2 ">주소</label>
						<div class="col-sm-5">
							<input type="text" name="addressAdr" class="form-control" id="sample5_address" placeholder="address">
							<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
							<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>	
						</div>
				</div>
				<br>
				
				<button type="submit" class="btn btn-primary">주소 추가</button>
				<br>
			</form>
			<br>
		</div>
	</main>
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff78dada6695be9bb2ffba5a1308b143&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script>
</html>