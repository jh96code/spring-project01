<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
	<style>
		.score_container{
			border : 1px solid black;
			height : 130px;
			padding : 15px 10px;
			
		}
		.score{
			margin : 5px 10px;
			font-size : 15px;
		}
		.button{
			padding : 10px;
		}
	</style>
<title>관광지</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/view/header.jsp"/>
	</header>
	<main>
	<div class="container w-75 mt-5 mx-auto">
		<h2>${Spot.name}</h2>
		<hr>
		<div class="card w-75 mx-auto">

			<img  src="resources/img/${Spot.img}" style="width:500px;height:400px;">
			<div id="map" style="width:500px;height:400px;"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff78dada6695be9bb2ffba5a1308b143&libraries=services"></script>
			<div class="card-body">
				
				<p class="card-text">장소 : ${Spot.address}
				<p>
				<p class="card-text">전화번호: ${Spot.number}</p>
				<p class="card-text">운영시간: ${Spot.time}</p>
				<p class="card-text">설명: ${Spot.desc}</p>
			</div>
		</div>
		<hr>
		<div class="score_container">
		<form action="score?aid=${Spot.aid}" method="post" >		
			<p>이 페이지에서 제공하는 정보에 대하여 어느정도 만족하셨습니까?</p>
			<ul>
				<li class="score"><input type="radio" name="score"value="5">5</li>
				<li class="score"><input type="radio" name="score" value="4">4</li>
				<li class="score"><input type="radio" name="score" value="3">3</li>
				<li class="score"><input type="radio" name="score" value="2">2</li>
				<li class="score"><input type="radio" name="score" value="1">1</li>
			</ul>
			<input type="text" class="searchbar" placeholder="문의사항을 적어주세요" name="questions" style=" padding:0px 50px;">
			<input type="hidden" name="name" value="${Spot.name}">
			<input type="submit" value="보내기" class="searchbutton">		
		</form>
		</div>
		<hr>
		<div style="display: flex;">
		<a href="javascript:history.back()" class="btn btn-primary"  style="margin: 0 10px;">Back</a> 
			
			<% 
				boolean admincheck = false;

				if (session.getAttribute("admincheck") != null)
					admincheck = (boolean) session.getAttribute("admincheck");

				if (admincheck == true) {
				%>
	
				<a href="updatespotsView?aid=${Spot.aid}" class="btn btn-primary" style="margin: 0 10px;">관광지수정</a> 
				<a href="delspots?aid=${Spot.aid}"class="btn btn-primary" style="margin: 0 10px;">관광지삭제</a>
				<form action="Questionview" method="post" >	
					<input type="hidden" name="name" value="${Spot.name}">
			 		<input type="submit" value="문의글" class="btn btn-primary" style="margin: 0 10px;">	
				</form>
			</div>
		<%
		}
		%>
		</div>
	</div>
	</main>
	<footer>
	<jsp:include page="/WEB-INF/view/footer.jsp" />
	</footer>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('${Spot.address}', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">${Spot.name}</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
	</script>
</body>
</html>