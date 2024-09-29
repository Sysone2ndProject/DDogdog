<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:HOTEL 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/updateHotel.css"/>
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="title">
        <p>호텔 정보 수정</p>
    </div>
    <form class="radius shadow" id="hotelUpdateForm" enctype="multipart/form-data" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="hotel-info-div">
            <div class="main-img-div">
                <i class="fa-solid fa-xmark" id="imgChangeIcon" onclick="openFileUpload()"></i>
                <img id="hotelImg" src="${hotel.mainImage}" alt="HotelImg">
                <input type="file" class="form-control" id="imageUpload" accept="image/*" onchange="previewImage(event)">
            </div>
            <div class="info-div">
                <div class="form-group">
                    <label for="businessName">상호명</label>
                    <input type="text" class="form-control" id="businessName" value="${hotel.businessName}">
                </div>
                <div class="form-group">
                    <p>사업자번호</p>
                    <p id="businessNumber">${hotel.businessNumber}<p>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">사업장연락처</label>
                    <input type="text" class="form-control" id="phoneNumber" maxlength="11" value="${hotel.phoneNumber}"
                           oninput="phoneNumberCheck()">
                </div>
                <div class="form-group">
                    <label for="address">사업장주소<span>(주소 찾기를 통해서만 변경가능합니다)</span></label>
                    <input type="text" class="form-control" id="address" value="${hotel.fullAddress}" readonly>
                    <button type="button" class="address-btn" onclick="findAddress()">주소 찾기</button>
                </div>
            </div>
        </div>
        <div class="hotel-intro-div">
            <div class="form-group">
                <label for="intro">호텔 소개글</label>
                <textarea class="form-control" id="intro">${hotel.intro}</textarea>
            </div>
        </div>
        <button type="submit" class="button radius">수정하기</button>

        <div class="address-hidden">
            <input type="hidden" id="hotelId" value="${hotel.hotelId}">
            <input type="hidden" id="addressId" value="${hotel.addressId}">
            <input type="hidden" id="sido">
            <input type="hidden" id="sigungu">
            <input type="hidden" id="dong">
        </div>
    </form>
</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/updateHotel.js"></script>
</html>