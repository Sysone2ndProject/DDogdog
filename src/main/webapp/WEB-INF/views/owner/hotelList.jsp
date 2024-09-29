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
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/hotelList.css"/>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="hotel-list-title">
        <p class="title"><security:authentication property="principal.ownerName"/>님의 Hotel</p>
        <button type="button" class="radius" onclick="location.href = '/v1/owners/hotels/form'"> 호텔 등록하기</button>
    </div>

    <div class="hotel-card-list">
        <c:forEach var="hotel" items="${hotels}">
            <div class="hotel-card shadow">
                <p class="hidden" id="hidden">${hotel.hotelId}</p>
                <div class="hotel-img">
                    <img src="${hotel.mainImage}" alt="Hotel-Img">
                </div>
                <div class="hotel-info">
                    <div class="hotel-card-title">
                        <p id="hotelName">${hotel.businessName}</p>
                        <p id="businessNumber">(${hotel.businessNumber})</p>
                    </div>

                    <div class="icon-text">
                        <i class="fa-solid fa-phone"></i>
                        <p>${hotel.phoneNumber}</p>
                    </div>

                    <div class="icon-text">
                        <i class="fa-solid fa-location-dot"></i>
                        <p>${hotel.fullAddress}</p>
                    </div>

                    <div class="icon-text">
                        <i class="fa-solid fa-thumbs-up"></i>
                        <p>${hotel.avgScore}(리뷰수: ${hotel.reviewCount})</p>
                    </div>

                    <div class="icon-text">
                        <i class="fa-solid fa-circle-info"></i>
                        <p>${hotel.intro}</p>
                    </div>
                </div>
                <div class="hotel-manage">
                    <button type="button" class="radius" onclick="location.href = '/v1/owners/hotels/${hotel.hotelId}'"> 호텔 정보 수정</button>
                    <button type="button" class="radius" onclick="location.href = '/v1/owners/rooms?hotelId=${hotel.hotelId}'"> 객실관리
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/hotelList.js"></script>
</html>