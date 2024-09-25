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
    <title>호텔 상세 조회 페이지</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/hotelDetail.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoId}&libraries=services"></script>
</head>
<body>
<jsp:include page="component/header.jsp"/>
<div class="hotel-detail">
    <div class="hotel">
        <div class="img-map">
            <img class="hotel-img radius shadow" src="${hotelDetail.hotel.mainImage}">
            <div class="map-box">
                <p>위치 정보</p>
                <div id="map"></div>
                <div class="loc">
                    <span class="material-icons-outlined">location_on</span>
                    <span class="text-info">${hotelDetail.hotel.fullAddress}</span>
                </div>
                <div class="cont">
                    <span class="material-icons-outlined">call</span>
                    <span class="text-info">${hotelDetail.hotel.phoneNumber}</span>
                </div>
            </div>
        </div>
        <div class="text-price">
            <div class="name-intro">
                <p class="hotel-name">${hotelDetail.hotel.businessName}</p>
                <div>
                    <span class="small">${hotelDetail.hotel.avgScore}</span>
                    <span class="small">(${hotelDetail.hotel.reviewCount} review)</span>
                </div>
                <p class="subtext">${hotelDetail.hotel.intro}</p>
            </div>
            <div class="price-btn">
                <p class="small">1일 최저가</p>
                <p class="price"><fmt:formatNumber value="${hotelDetail.hotel.price}" type="number"
                                                   groupingUsed="true"/>₩</p>
                <button class="sub-btn radius" type="button" onclick="goToForm()">객실 선택</button>
            </div>
        </div>
    </div>
</div>
<div class="room-detail">
    <div class="room" id="room">
        <div class="buttons">
            <button class="big-btn pink">Information</button>
            <button class="big-btn">Reviews</button>
        </div>
        <div class="choose-room">
            <p class="choose-room-title">객실 선택</p>
            <form id="reservationForm">
                <div class="date-pick radius shadow">
                    <label for="startDate">체크인</label>
                    <input id="startDate" type="date" onchange="setEndDate(); getStartDate();"/>
                    <label for="endDate">체크아웃</label>
                    <input id="endDate" type="date" onchange="getEndDate()">
                </div>
                <div class="room-list">
                    <c:forEach var="room" varStatus="status" items="${hotelDetail.rooms}">
                        <div class="room-item radius shadow">
                            <img class="room-img" src="${room.roomImage}">
                            <div class="room-info">
                                <p class="room-grade">${room.grade}</p>
                                <p class="subtext">${room.intro}</p>
                                <p class="small">1박</p>
                                <div class="price-box">
                                    <span class="room-price"><fmt:formatNumber value="${room.price}"
                                                                               type="number"
                                                                               groupingUsed="true"/>₩</span>
                                    <div class="count-btn">
                                        <button class="minus" type="button" onclick="decreaseValue(${status.index})">-</button>
                                        <input id="count${status.index}" type="number" min="0"
                                               value="0">
                                        <button class="plus" type="button" onclick="increaseValue(${status.index})">+</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="total-info">
                    <div class="total-price">
                        <span>총계: </span>
                        <span id="totalPrice" class="red">0</span><span class="red">₩</span>
                    </div>
                    <div class="total-count">
                        <span>총 개수: </span>
                        <span id="totalCount" class="red">0</span>
                    </div>
                </div>
                <div class="form-btn">
                    <button class="sub-btn radius" type="submit">예약하기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>
</body>
<script src="${pageContext.request.contextPath}/js/customers/hotelDetail.js"></script>
<script src="${pageContext.request.contextPath}/js/customers/dateHandler.js"></script>
<script src="${pageContext.request.contextPath}/js/customers/locationHandler.js"></script>
<script>
  <%--const address = ${hotelDetail}--%>
  address = `${hotelDetail.address.fullAddress}`;
  document.addEventListener("DOMContentLoaded", loadKakaoMap(address));
  const hotelId = parseInt('${hotelDetail.hotel.id}');
  let rooms = [
    <c:forEach var="room" items="${hotelDetail.rooms}" varStatus="status">
    {
      grade: "${room.grade}",
      count: 0,
      price: parseInt("${room.price}"),

    }<c:if test="${!status.last}">, </c:if>
    </c:forEach>
  ]
</script>
</html>
