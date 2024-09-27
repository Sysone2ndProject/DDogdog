<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>검색</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/hotel.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<form class="search-form" id="searchForm">
    <div class="text-box radius">
        <input class="text-input" type="text" id="keyword" value="${param.keyword}"
               placeholder="지역">
        <img class="small-icon" src="${pageContext.request.contextPath}/img/cancel.svg" alt="icon"
             onclick="deleteKeyword()"/>
    </div>
    <div class="period radius">
        <div class="date-pick">
            <input class="date-input" type="date" id="startDate"
                   value="${param.startDate}" onchange="setEndDate()"/>
        </div>
        <div class="date-pick">
            <input class="date-input" type="date" id="endDate"
                   value="${param.endDate}"/>
        </div>
    </div>
</form>

<hr>
<div class="search-result">
    <p class="title">검색 결과</p>
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
<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="/js/customers/hotel.js"></script>
<script src="/js/customers/dateHandler.js"></script>
</html>
