<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:Hotel 관리</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/hotelList.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <div class="hotel-list-title">
        <p class="title"><security:authentication property="principal.username"/>님의 Hotel</p>
        <button type="button" onclick="location.href = '/v1/owners/hotels/form'"> 호텔 등록하기</button>
    </div>
    <h2>${hotel.mainImage}</h2>
    <c:forEach var="hotel" items="${hotels}">
        <div class="hotel-card">
            <p class="hidden" id="hidden">${hotel.hotelId}</p>
            <div class="hotel-img">
                <img src="${hotel.mainImage}" alt="Hotel-Img">
            </div>
            <div class="hotel-info">
                <p id="hotel-name">${hotel.businessName}</p>
                <p>사업자 번호: ${hotel.businessNumber}</p>
                <p>연락처: ${hotel.phoneNumber}</p>
                <p>주소: ${hotel.fullAddress}</p>
                <p>평점: ${hotel.avgScore}(리뷰수: ${hotel.reviewCount})</p>
                <p>소개글: ${hotel.intro}</p>
            </div>
            <div class="hotel-manage">
                <button type="button" onclick="location.href = '/v1/owners/hotels/${hotel.hotelId}'"> 호텔 정보 수정</button>
                <button type="button" onclick="location.href = '/v1/owners/rooms?hotelId=${hotel.hotelId}'"> 객실관리</button>
            </div>
        </div>
    </c:forEach>
</div>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/hotelList.js"></script>
</html>