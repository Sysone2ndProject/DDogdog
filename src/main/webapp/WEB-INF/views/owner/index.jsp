<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/owners/index.css"/>
    <link rel="stylesheet" type="text/css"
          href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript"
            src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="content">
    <div class="slider shadow">
        <img class="main-img" src="${pageContext.request.contextPath}/img/owner/main1.svg">


        <img class="main-img" src="${pageContext.request.contextPath}/img/owner/main2.svg">

        <img class="main-img" src="${pageContext.request.contextPath}/img/owner/main3.svg">
    </div>
    <div class="menu">
        <div class="date-box shadow radius">
            <div class="date">
                <p id="nowDate">
                    오늘 날짜
                </p>
            </div>
            <div class="day-box">
                <span id="nowDay">요일</span><img class="menu-img"
                                                src="${pageContext.request.contextPath}/img/owner/corgi.svg">
            </div>
        </div>
        <div class="main-menu-box shadow radius">
            <sec:authorize access="isAnonymous()">
                <div class="main-login-btn">
                    <p>서비스를 이용하려면<br> 로그인 해주세요</p>
                    <button class="button radius"
                            onclick="location.href='/v1/owners/login'">로그인/회원가입
                    </button>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="main-login">
                    <p class="welcome-text">
                    <span class="user-name"><sec:authentication property="principal.username"/></span> 사장님 환영합니다
                    </p>
                    <button class="main-btn radius" type="button" onclick="location.href='/v1/owners/hotels/'">호텔 및 객실 관리</button>
                    <button class="main-btn radius" type="button" onclick="location.href='/v1/owners/reservations/form'">예약 관리</button>
                    <button class="main-btn radius" type="button" onclick="location.href='/v1/owners/reservations/statics'">매출 통계</button>
                    <div class="main-logout" onclick="location.href='/v1/logout'">
                        <span>로그아웃</span>
                        <span class="material-icons"
                              >logout</span>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>


<script type="text/javascript">
  $(document).ready(function () {
    $('.slider').slick({
      arrows: false,
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 5000,
    });
  });
</script>

<%--<body>--%>
<%--<div class="header-box">--%>
<%--    <jsp:include page="component/header.jsp"></jsp:include>--%>
<%--</div>--%>
<%--<!-- 테스트용 입니다. -->--%>
<%--<div class="container">--%>
<%--    <div class="button">--%>
<%--        <i class="fa-solid fa-chevron-left prev-button" onclick="prevSlide()"></i>--%>
<%--        &lt;%&ndash;        <button class="prev-button" onclick="prevSlide()">Prev</button>&ndash;%&gt;--%>
<%--    </div>--%>
<%--    <div class="carousel">--%>
<%--        <div class="cell">--%>
<%--            <div class="cell-img">--%>
<%--                <img src="${pageContext.request.contextPath}/img/owner/customerMain.png" alt="슬라이드 1"/>--%>
<%--            </div>--%>
<%--            <div class="cell-comment">--%>
<%--                <p>고객의 접근이 쉽고 빠르고 쉽게 예약 가능</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="cell">--%>
<%--            <div class="cell-img">--%>
<%--                <img src="${pageContext.request.contextPath}/img/owner/hotelSearch.png" alt="슬라이드 2"/>--%>
<%--            </div>--%>
<%--            <div class="cell-comment">--%>
<%--                <p>한눈에 보는 나의 호텔 매출 관리</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="cell">--%>
<%--            <div class="cell-img">--%>
<%--                <img src="${pageContext.request.contextPath}/img/owner/customerMain.png" alt="슬라이드 3"/>--%>
<%--            </div>--%>
<%--            <div class="cell-comment">--%>
<%--                <p>고객의 리뷰를 통한 피드백 제공 관리</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="cell">--%>
<%--            <div class="cell-img">--%>
<%--                <img src="${pageContext.request.contextPath}/img/owner/hotelSearch.png" alt="슬라이드 4"/>--%>
<%--            </div>--%>
<%--            <div class="cell-comment">--%>
<%--                <p>고객의 인기 호텔, 룸 타입 등 트렌드 관리</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="button">--%>
<%--        <i class="fa-solid fa-chevron-right next-button" onclick="nextSlide()"></i>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div class="footer-box">--%>
<%--    <jsp:include page="component/footer.jsp"></jsp:include>--%>
<%--</div>--%>
<%--</body>--%>
</body>
<script src="${pageContext.request.contextPath}/js/owners/index.js"></script>
</html>
