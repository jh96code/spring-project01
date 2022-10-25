<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href="../resources/static/css/style.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script type="text/javascript">
		function checkForm() {
			if (!document.addMemberForm.id.value) {
				alert("아이디를 입력하세요.");
				return false;
			}
	
			if (!document.addMemberForm.password.value) {
				alert("비밀번호를 입력하세요.");
				return false;
			}
	
			if (document.addMemberForm.password.value != document.addMemberForm.password_confirm.value) {
				alert("비밀번호를 동일하게 입력하세요.");
				return false;
			}
			
			if (!document.addMemberForm.name.value) {
				alert("이름을 입력하세요.");
				return false;
			}
			
			if (!document.addMemberForm.gender.value) {
				alert("성별을 클릭하세요.");
				return false;
			}
			if (!document.addMemberForm.year.value, !document.addMemberForm.month.value, !document.addMemberForm.day.value) {
				alert("생일을 입력하세요.");
				return false;
			}
			
			if (!document.addMemberForm.mail.value) {
				alert("이메일을 입력하세요.");
				return false;
			}
			
			if (!document.addMemberForm.phone.value) {
				alert("전화번호를 입력하세요.");
				return false;
			}
			
	        if (confirm("회원가입 하시겠습니까?")) {
	            alert("회원가입이 완료되었습니다.");
	            document.addMemberForm.submit();
	        }
	    }
	</script>
	<title>회원 가입</title>
	<style>
		.id_check{
			text-decoration: none !important;
		    border: 1px solid #333;
		    border-radius: 3px;
		    line-height: 30px;
		    padding: 0px 10px;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class="container">
		<br>
		<h1>회원가입</h1>
		<hr><br>
		<form name="addMemberForm" class="form-horizontal"  action="processAddMember" method="post" onsubmit="return checkForm()">
			<div class="form-group  row">
				<label class="col-sm-2 ">아이디</label>
				<div class="col-sm-3">
					<input name="id" type="text" class="form-control" placeholder="id" >
				</div>
				<a href="#" class="btn btn-success" onclick="window.open('idCheck','','resizable=0 width=500 height=300 left=600 top=300');return false">중복확인</a>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input name="password" type="text" class="form-control" placeholder="password" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">비밀번호확인</label>
				<div class="col-sm-3">
					<input name="password_confirm" type="text" class="form-control" placeholder="password confirm" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성명</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control" placeholder="name" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성별</label>
				<div class="col-sm-10">
					<input name="gender" type="radio" value="남" /> 남 
					<input name="gender" type="radio" value="여" /> 여
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">생일</label>
				<div class="col-sm-4 ">
					<input type="text" name="year" maxlength="4" placeholder="년(4자)" size="6"> 
					<select name="month">
						<option value="">월</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select> <input type="text" name="day" maxlength="2" placeholder="일" size="4">
				</div>
			</div>
			<div class="form-group  row ">
				<label class="col-sm-2">이메일</label>
				<div class="col-sm-10">
					<input type="text" name="mail1" maxlength="50" placeholder="email">@ 
					<select name="mail2">
						<option>naver.com</option>
						<option>daum.net</option>
						<option>gmail.com</option>
						<option>nate.com</option>
					</select>
				</div>				
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">전화번호</label>
				<div class="col-sm-3">
					<input name="phone" type="text" class="form-control" placeholder="phone" >

				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 ">주소</label>
				<div class="col-sm-5">
					<input type="text" name="address" class="form-control" id="sample5_address" placeholder="address">
					<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
					<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>	
				</div>
			</div>
			<div class="from-control">
				<b>관리자 권한 계정으로 회원가입 하시겠습니까?</b> &nbsp;&nbsp;
				<label for="yes">네</label>
				<input name="admincheck" id="yes" type="checkbox" class="form-control" style="width:20px; display:inline-block; margin-top:5px">
			</div> <br>
			<br>
			<div class="form-group  row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary " value="등록 " onclick="addMember()"> &nbsp;
					<input type="reset" class="btn btn-primary " value="취소 " onclick="reset()" >
				</div>
			</div>
		</form>
		<br>
	</div>
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