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
          href="${pageContext.request.contextPath}/customers/css/index.css">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<form class="search" action="/v1/customers/hotels" method="get">
    <div class="search-bar radius">
        <img class="icon" src="${pageContext.request.contextPath}/img/search.svg"
             alt="search-icon"/>
        <input class="text-input" name="keyword" placeholder="지역을 입력해 주세요">
    </div>
    <div class="period radius">
        <div class="date-pick">
            <img class="icon" src="${pageContext.request.contextPath}/img/check_in.svg"
                 alt="check-in-icon"/>
            <label class="date-label" for="start">체크인</label>
            <input class="date-input" type="date" id="start"
                   name="startDate" min="2024-09-11"
                   max="2025-09-11"/>
        </div>
        <div class="date-pick left">
            <img class="icon" src="${pageContext.request.contextPath}/img/check_out.svg"
                 alt="check-out-icon"/>
            <label class="date-label" for="end">체크아웃</label>
            <input class="date-input" type="date" id="end" name="endDate"
                   min="2024-09-11"
                   max="2025-09-11"/>
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
            <div class="hotel radius shadow">
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
<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
</html>
