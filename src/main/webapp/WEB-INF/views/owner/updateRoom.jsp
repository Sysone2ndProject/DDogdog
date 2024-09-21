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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/updateRoom.css"/>
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
    <p id="title">${room.grade} 객실 정보 수정</p>
    <form id="hotelRegisterForm" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="room-info-div">
            <div class="room-img-div">
                <i class="fa-solid fa-xmark" id="img-change-icon" onclick="openFileUpload()"></i>
                <img id="room-img" src="${room.roomImage}" alt="room-Img">
                <input type="file" id="image-upload" accept="image/*" onchange="previewImage(event)">
            </div>
            <div class="info-div">
                <div class="form-group">
                    <label for="room-count">객실 수:</label>
                    <input type="text" id="room-count" value="${room.roomCount}">
                </div>
                <div class="form-group">
                    <label for="max-dogs">최대 견수:</label>
                    <input type="text" id="max-dogs" value="${room.maxDogs}">
                </div>
                <div class="form-group">
                    <label for="price">가격:</label>
                    <input type="text" id="price" value="${room.price}" readonly>
                </div>
            </div>
        </div>
        <div class="room-intro-div">
            <div class="form-group">
                <label for="intro">객실 소개글:</label>
                <textarea class="form-control" id="intro">${room.intro}</textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-success">수정하기</button>
        <div class="address-hidden">
            <input type="hidden" id="hotel-id" value="${hotelId}">
        </div>
    </form>
</div>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/updateRoom.js"></script>
</html>