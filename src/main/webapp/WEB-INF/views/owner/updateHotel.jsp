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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <p id="title">호텔 정보 수정</p>
    <form id="hotel-update-form" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="hotel-info-div">
            <div class="main-img-div">
                <i class="fa-solid fa-xmark" id="img-change-icon" onclick="openFileUpload()"></i>
                <img id="hotel-img" src="${hotel.mainImage}" alt="Hotel-Img">
                <input type="file" id="image-upload" accept="image/*" onchange="previewImage(event)">
            </div>
            <div class="info-div">
                <div class="form-group">
                    <label for="business-name">상호명:</label>
                    <input type="text" id="business-name" value="${hotel.businessName}">
                </div>
                <div class="form-group">
                    <label for="business-number">사업자번호:</label>
                    <input type="text" id="business-number" value="${hotel.businessNumber}" readonly>
                </div>
                <div class="form-group">
                    <label for="phone-number">사업장연락처:</label>
                    <input type="text" id="phone-number" value="${hotel.phoneNumber}">
                </div>
                <div class="form-group">
                    <label for="address">사업장주소 :주소 찾기를 통해서만 변경가능합니다.</label>
                    <input type="text" class="form-control" id="address" value="${hotel.fullAddress}" readonly>
                    <button type="button" onclick="findAddress()">주소 찾기</button>
                </div>
            </div>
        </div>
        <div class="hotel-intro-div">
            <div class="form-group">
                <label for="intro">호텔 소개글:</label>
                <textarea class="form-control" id="intro">${hotel.intro}</textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-success">수정하기</button>
        <div class="address-hidden">
            <input type="hidden" id="hotel-id" value="${hotel.hotelId}">
            <input type="hidden" id="sido">
            <input type="hidden" id="sigungu">
            <input type="hidden" id="dong">
        </div>
    </form>
</div>


<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/updateHotel.js"></script>
</html>