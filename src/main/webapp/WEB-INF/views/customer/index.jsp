<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hotel</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/index.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<form class="search" id="searchForm">
    <div class="search-bar radius">
        <img class="icon" src="${pageContext.request.contextPath}/img/search.svg"
             alt="search-icon"/>
        <input class="text-input" id="keyword" name="keyword" placeholder="지역을 입력해 주세요">
    </div>
    <div class="period radius">
        <div class="date-pick">
            <img class="icon" src="${pageContext.request.contextPath}/img/check_in.svg"
                 alt="check-in-icon"/>
            <label class="date-label" for="startDate">체크인</label>
            <input class="date-input" type="date" id="startDate" onchange="setEndDate()"/>
        </div>
        <div class="date-pick left">
            <img class="icon" src="${pageContext.request.contextPath}/img/check_out.svg"
                 alt="check-out-icon"/>
            <label class="date-label" for="endDate">체크아웃</label>
            <input class="date-input" type="date" id="endDate"/>
        </div>
    </div>
    <div class="button-block">
        <input class="button radius" type="submit" value="검색하기">
    </div>
</form>

<div class="best">
    <p class="title">베스트 호텔</p>
    <div class="hotel-list">
        <c:forEach var="hotel" items="${hotels}">
            <div class="hotel radius shadow" onclick="gotoDetail(${hotel.id})">
                <img class="thumnail" src="${pageContext.request.contextPath}/img/logo.png">
                <div class="content">
                    <h3>${hotel.businessName}</h3>
                    <p>${hotel.intro}</p>
                    <div class="score">
                        <span>${hotel.avgScore}</span>
                        <span>(${hotel.reviewCount})</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:if test="${not empty localHotels}">
    <hr>
    <div class="best">
        <p class="title">우리 동네 베스트 호텔</p>
        <div class="hotel-list">
            <c:forEach var="hotel" items="${localHotels}">
                <div class="hotel radius shadow" onclick="gotoDetail(${hotel.id})">
                    <img class="thumnail" src="${pageContext.request.contextPath}/img/logo.png">
                    <div class="content">
                        <h3>${hotel.businessName}</h3>
                        <p>${hotel.intro}</p>
                        <div class="score">
                            <span>${hotel.avgScore}</span>
                            <span>(${hotel.reviewCount})</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="/js/customers/index.js"></script>
<script src="/js/customers/dateHandler.js"></script>
</html>
