<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>홈 페이지</title>

    <!-- css 모음-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/section01.css" />
    <link rel="stylesheet" href="./resources/css/section02.css" />
    <link rel="stylesheet" href="./resources/css/section03.css" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
    
    <!-- js 모음 -->
    <script src="https://kit.fontawesome.com/9eb162ac0d.js" crossorigin="anonymous" ></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
    
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
    <!-- 섹션1 시작 -->
    <section id="section1">
		<div class="swiper mySwiper swiper-container">
            <ul class="swiper-wrapper">
                <li class="swiper-slide" style="background-image: url(./resources/img/slide01.jpg)">
                    <a href="https://www.changwon.go.kr/depart/contents.do?mId=1101010000" target="_blank">
                        <div class="slide-text-box">
                            <p>군항도시에서 함께 즐기는 <br>세계 최대 벚꽃 축제</p>
                            <h4>진해군항제</h4>
                            <div class="button">자세히 보기 &nbsp;></div>
                        </div>
                    </a>
                </li>
                <li class="swiper-slide" style="background-image: url(./resources/img/slide02.jpg)">
                    <a href="https://blog.naver.com/changwonhouse" target="_blank">
                        <div class="slide-text-box">
                            <p>관람,체험할수 있는 <br>창원역사민속박물관</p>
                            <h4>창원의집</h4>
                            <div class="button">자세히 보기 &nbsp;></div>
                        </div>
                    </a>
                </li>
                <li class="swiper-slide" style="background-image: url(./resources/img/slide03.jpg)">
                    <a href="https://culture.changwon.go.kr/index.changwon?contentId=130&bbsId=BBSMSTR_000000000082&nttId=742&menuNo=11010000   " target="_blank">
                        <div class="slide-text-box">
                            <p>타임머신을 타고 가야시대로 <br>거슬러 가는 여행지</p>
                            <h4>해양드라마세트장</h4>
                            <div class="button">자세히 보기 &nbsp;></div>
                        </div>
                    </a>
                </li>
            </ul>
            <div class="swiper-pagination"></div>
        </div>
    </section>
    <!-- 섹션1 끝 -->
    
    <!-- 섹션2 시작 -->
    <section id="section2">
        <div class="container" align="center">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>창원 관광 안내</h2>
                        <span>CHANGWON TOUR GUIDE</span>
                    </div>
                </div>
            </div>
            <div class="row">
           		<div class="col-lg-4 col-sm-6">
                    <a href="https://culture.changwon.go.kr/index.changwon?menuId=12120000" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-map-marker-alt"></i>
	                            <h4>관광지도</h4>
	                            <p>창원의 관광지를 한눈에 볼 수 있도록<br>만든 지도로 관광정보를 보실 수 있어요.</p>
	                    </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="https://www.changwoncitytour.com/" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-archway"></i>
	                            <h4>창원시티투어</h4>
	                            <p>전문 시티투어해설사와 함께<br>유명관광지를 한번에 즐겨보세요.</p>
	                    </div>
                    </a>    
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="https://culture.changwon.go.kr/index.changwon?menuId=12150000" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-passport"></i>
	                            <h4>여행작가가 추천하는 관광코스</h4>
	                            <p>여행작가가 추천하는 관광코스로<br>색다르고 낭만가득한 창원을 만나보세요.</p>
	                    </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="https://culture.changwon.go.kr/index.changwon?menuId=17040000" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-bus"></i>
	                            <h4>교통정보</h4>
	                            <p>버스, 택시, 열차, 항공기, 여객선 등<br>창원의 교통정보를 알려드립니다.</p>
	                    </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="https://culture.changwon.go.kr/index.changwon?menuId=17020000" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-map-marked-alt"></i>
	                            <h4>관광안내지도신청</h4>
	                            <p>창원의 문화재와 주요관광지 자료가 <br>소개되어 있는 관광안내지도를 제공합니다.</p>
	                    </div>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <a href="https://culture.changwon.go.kr/index.changwon?menuId=17010001" target="_blank">
	                    <div class="service-item">
	                        <i class="fas fa-glass-cheers"></i>
	                            <h4>주말나들이</h4>
	                            <p>이번 주말 어디로 갈지 고민중이신가요?<br>창원에서의 나들이를 추천해드립니다.</p>
	                    </div>
                    </a>
                </div>
            </div>
        </div>
    </section>
    <!-- 섹션2 끝 -->
    
    <!-- 섹션3 시작-->
    <section id="section3">
        <div class="wrap">
            <div class="left">
                <div class="box">
                    <h1>AUGUST_LAMB</h1>
                    <h2>어거스트램</h2>
                    <p>우리의 도시 마창진을 담아낸 기념품과 찐로컬 <br>작가님들의 아기자기한 소품, 경남 로컬업체들의 <br>퀄리티 있는 지역제품들도 함께 만날수있는 마창진 기념품샵</p>
                    <a href="https://www.instagram.com/august_lamb_masan/" class="more" target="_blank">Read More &raquo;</a>
                </div>
            </div>
            <div class="right">
                <div class="img" style="background-image: url(./resources/img/goodsshop01.jpg);"></div>
            </div>
        </div>
        <div class="wrap">
            <div class="left">
                <div class="img" style="background-image: url(./resources/img/goodsshop02.jpg);"></div>
            </div>
            <div class="right">
                <div class="box">
                    <h1>NC DINOS STORE</h1>
                    <h2>NC 팀스토어</h2>
                    <p>명문 프로야구단 NC 다이노스 팀의 <br>유니폼, 모자, 야구용품 기획상품 등 판매하는 운영 공식샵</p>
                    <a href="https://shop.ncdinos.com/" class="more" target="_blank">Read More &raquo;</a>
                </div>
            </div>
        </div>
    </section>
    <div id="go_top">
	    <div>
	        <p>↑<br>TOP</p>
	   	</div>
	</div>
    <!-- 섹션3 끝-->
	<jsp:include page="/WEB-INF/view/footer.jsp"/>
	<!-- 제이쿼리 cdn 들고오기 -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>

	<script>
        var swiper = new Swiper(".mySwiper", {
            slidesPerView: "auto",
            centeredSlides: true,
            spaceBetween: 30,
            loop : true,
            autoplay: {
                delay: 3000,
                disableOnInteraction: false,
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
        });
        
        var navbar = $("header").offset().top + 100;
        var section2 = $("#section2").offset().top - 200;
        var section3 = $("#section3").offset().top - 350;
        var go_top = $("#go_top").offset().top - 250;
        $(window).scroll(function(){
            var top = $(this).scrollTop();
            console.log(top);
            if(top > navbar){
                $("header").css("background-color", "#fdfdfd");
            } else{
                $("header").css("background-color", "#fff");
            }
            if(top > section2){
                $("#section2 .col-lg-4").css("opacity", "1");
                $("#section2 .col-lg-4").css("transform", "translateY(0px)");
            }
            if(top > section3){
                $("#section3 .wrap").css("opacity", "1");
                $("#section3 .wrap").css("transform", "translateY(0px)");
            }
            if(top > go_top){
                $("#go_top").css("display", "block");
                $("#go_top").css("opacity", "1");
            } else{
                $("#go_top").css("display", "none");
                $("#go_top").css("opacity", "0");
            }
        });

	    $("#go_top").on("click",function(){
	        $("html, body").animate({scrollTop: 0}, 300);
	    })
    </script>
</body>
</html>