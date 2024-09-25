<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoId}&libraries=services"></script>

</head>
<body>
<jsp:include page="component/header.jsp"/>
<div class="search-bg">
    <div class="search-section">
        <div class="search-title-box">
            <p class="search-title">지금 바로 검색하세요<img class="title-icon"
                                                    src="${pageContext.request.contextPath}/img/paw.svg"/>
            </p>
        </div>
        <form id="searchForm" class="search-box">
            <div class="date-pick radius">
                <label class="date-label" for="startDate">체크인</label>
                <input class="date-input" type="date" id="startDate" onchange="setEndDate()"/>
                <label class="date-label" for="endDate">체크아웃</label>
                <input class="date-input" type="date" id="endDate" onclick="checkStartDate()"/>
            </div>
            <input type="text" class="text-input radius" id="keyword" placeholder="장소를 입력해 주세요"/>
            <button class="button radius" type="submit">검색</button>
        </form>
    </div>
</div>

<div class="popular-hotels">
    <p class="small-title">TRENDY</p>
    <p class="title">지금 인기있는 호텔</p>
    <div class="hotel-list">
        <c:forEach var="hotel" items="${hotels}">
            <div class="hotel-card shadow radius">
                <div class="img-container">
                    <img class="thumnail" src="${hotel.mainImage}">
                </div>
                <div class="content">
                    <div class="hotel-info">
                        <div class="info-box">
                            <div class="days">
                                <span class="material-icons-outlined">calendar_today</span>
                                <span class="text-info">1 Day</span>
                            </div>
                            <div class="reviews">
                                <span class="material-icons-outlined">person</span>
                                <span class="text-info">${hotel.reviewCount} Reviewed</span>
                            </div>
                        </div>
                    </div>
                    <p class="hotel-name">${hotel.businessName}</p>
                    <div class="loc">
                        <span class="material-icons-outlined">location_on</span>
                        <span class="hotel-loc">${hotel.fullAddress}</span>
                    </div>
                    <p class="hotel-price">${hotel.price}₩ ~ </p>

                    <button class="button radius" type="button" onclick="gotoDetail(${hotel.id})">
                        자세히 보기
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <p>${dto.kakaoJsId}</p>
    <div class="nearby-hotels">
        <div class="nearby-content">
            <p class="small-title">NEAR BY</p>
            <p class="title">우리 동네 호텔</p>
            <p class="small"> 고객님의 위치 기반 근처 호텔 검색 결과입니다.</p>
            <button class="button radius">주소 수정하기</button>
            <div class="nearby-map">
                <div class="map-box">
                    <p id="addressDisplay"></p>
                    <div id="map"></div>
                </div>
                <c:if test="${not empty localHotels}">
                    <div class="nearby-list">
                        <c:forEach var="hotel" items="${localHotels}">
                            <div class="nearby-card radius">
                                <div class="nearby-info">
                                    <p class="name">${hotel.businessName}</p>
                                    <div class="info">
                                        <div class="loc">
                                            <span class="material-icons-outlined">location_on</span>
                                            <span class="hotel-loc">${hotel.fullAddress}</span>
                                        </div>
                                        <div class="cont">
                                            <span class="material-icons-outlined">call</span>
                                            <span class="text-info">${hotel.phoneNumber}</span>
                                        </div>
                                    </div>
                                    <div class="price-btn">
                                        <p class="price">${hotel.price}₩</p>
                                        <img class="paws"
                                             src="${pageContext.request.contextPath}/img/paw.svg"
                                             onclick="gotoDetail(${hotel.id})"/>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</sec:authorize>

<jsp:include page="component/footer.jsp"/>
</body>
<script>
  <sec:authorize access="isAuthenticated()">
  let addressId = <sec:authentication property="principal.customerDTO.addressId"/>;
  </sec:authorize>
</script>
<script src="${pageContext.request.contextPath}/js/customers/index.js"></script>
<script src="${pageContext.request.contextPath}/js/customers/dateHandler.js"></script>
<script src="${pageContext.request.contextPath}/js/customers/locationHandler.js"></script>
</html>
