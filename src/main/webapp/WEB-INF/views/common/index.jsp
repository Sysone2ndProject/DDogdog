<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Ddogdog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/common/index.css?v=1.0.1">
</head>

<body>
<div class="swiper mySwiper">
    <div class="swiper-wrapper">
        <div class="background first swiper-slide">
            <div class="container">
                똑독
            </div>
            <div class = "maintext">
                국내 최초 <br>애견 호텔<br>예약 중개 서비스
            </div>
            <br>
        </div>
        <div class="background second swiper-slide">
            <div class="container2">
                똑독
            </div>
            <div class = "maintext">
                검색도<br> 예약도<br> 똑독하게
            </div>
            <div class="subtext">
                    강아지를 맡아줄 사람이없다면?
                    <br>
                    추천 검색 예약까지
                    <br>
                    간편하게
            </div>
                <div class="buttons">
                    <button class="learn-more" onclick="location.href='/v1/customers';">예약 바로가기</button>
                </div>

        </div>
        <div class="background third swiper-slide">
            <div class="container2">
                똑독
            </div>
            <div class = "maintext">
                영업도<br> 관리도<br> 똑독하게
            </div>
            <div class="subtext">
                    업주이신가요?
                    <br>
                    지금 바로 호텔을 등록하시고
                    <br>
                    예약 수익 관리까지 한번에 처리하세요
            </div>
            <div class="buttons">
                <button class="learn-more" onclick="location.href='/v1/owners';">호텔 등록 바로가기</button>
            </div>

        </div>
        <div class="background four swiper-slide">
            <h1>지금 바로 시작하세요</h1>
                    <button class="learn-more-four" onclick="location.href='/v1/customers';">예약 바로가기</button>
                    <button class="learn-more-four"onclick="location.href='/v1/owners';">호텔 등록 바로가기</button>
        </div>
    </div>
    <div class="swiper-pagination"></div>
</div>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="/js/common/index.js"></script>
</body>

</html>
