<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>검색 결과</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/hotel.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <form class="search-form" id="searchForm">
        <div class="text-box radius shadow">
            <input class="text-input" type="text" id="keyword" value="${param.keyword}"
                   placeholder="지역 / 호텔명" autocomplete="off" onchange="searchAutoComplete()">
            <div id="autoSearchArea" class="select-area radius"></div>
            <span class="material-icons-outlined" onclick="deleteKeyword()">cancel</span>
        </div>
        <div class="date-pick radius shadow">
            <label for="startDate">체크인</label>
            <input id="startDate" type="date" value="${param.startDate}"
                   onchange="setEndDate(); getStartDate();"/>
            <label for="endDate">체크아웃</label>
            <input id="endDate" type="date" value="${param.endDate}" oninput="getEndDate()">
        </div>
        <button class="submit-btn radius shadow" type="submit">검색</button>
    </form>
    <div class="search-result">
        <p class="hotel-title">" <span class="keyword">${param.keyword}</span> " 에 대한 검색 결과</p>
        <div class="hotel-list" id="hotelList">
            <c:forEach var="hotel" items="${hotels.content}">
                <div class="hotel radius shadow" onclick="gotoDetail(${hotel.id})">
                    <img class="hotel-img" src="${hotel.mainImage}">
                    <div class="hotel-content">
                        <p class="hotel-name">${hotel.businessName}</p>
                        <div class="loc">
                            <span class="material-icons-outlined">location_on</span>
                            <span class="text-info">${hotel.fullAddress}</span>
                        </div>
                        <div class="cont">
                            <span class="material-icons-outlined">call</span>
                            <span id="phone" class="text-info">${hotel.phoneNumber}</span>
                        </div>
                        <p class="hotel-intro">${hotel.intro}</p>
                    </div>
                    <div class="price-btn">
                        <div class="price-title">
                            <span class="material-icons-outlined">calendar_today</span>
                            <span class="text-info">1 Day</span>
                        </div>
                        <p class="hotel-price"><fmt:formatNumber value="${hotel.price}"
                                                                 type="number"
                                                                 groupingUsed="true"/> ₩</p>
                        <button class="button radius">자세히 보기</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>
</body>
<script src="/js/customers/hotel.js"></script>
<script src="/js/customers/dateHandler.js"></script>
<script src="${pageContext.request.contextPath}/js/customers/searchAutocomplete.js"></script>
<script>
  const paramKeyword = "${param.keyword}";
</script>
</html>
