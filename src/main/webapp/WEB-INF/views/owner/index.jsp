<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/index.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <div class="button">
        <i class="fa-solid fa-chevron-left prev-button" onclick="prevSlide()"></i>
<%--        <button class="prev-button" onclick="prevSlide()">Prev</button>--%>
    </div>
    <div class="carousel">
        <div class="cell">
            <div class="cell-img">
                <img src="${pageContext.request.contextPath}/img/owner/customerMain.png" alt="슬라이드 1"/>
            </div>
            <div class="cell-comment">
                <p>고객의 접근이 쉽고 빠르고 쉽게 예약 가능</p>
            </div>
        </div>
        <div class="cell">
            <div class="cell-img">
                <img src="${pageContext.request.contextPath}/img/owner/hotelSearch.png" alt="슬라이드 2"/>
            </div>
            <div class="cell-comment">
                <p>한눈에 보는 나의 호텔 매출 관리</p>
            </div>
        </div>
        <div class="cell">
            <div class="cell-img">
                <img src="${pageContext.request.contextPath}/img/owner/customerMain.png" alt="슬라이드 3"/>
            </div>
            <div class="cell-comment">
                <p>고객의 리뷰를 통한 피드백 제공 관리</p>
            </div>
        </div>
        <div class="cell">
            <div class="cell-img">
                <img src="${pageContext.request.contextPath}/img/owner/hotelSearch.png" alt="슬라이드 4"/>
            </div>
            <div class="cell-comment">
                <p>고객의 인기 호텔, 룸 타입 등 트렌드 관리</p>
            </div>
        </div>
    </div>
    <div class="button">
        <i class="fa-solid fa-chevron-right next-button" onclick="nextSlide()"></i>
    </div>
</div>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/index.js"></script>
</html>