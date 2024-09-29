<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:객실 정보 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/updateRoom.css"/>
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="title">
        <p id="title">${room.grade}</p>
    </div>

    <form class="radius shadow" id="updateRoomForm" enctype="multipart/form-data" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="room-info-div">
            <div class="room-img-div">
                <i class="fa-solid fa-xmark" id="img-change-icon" onclick="openFileUpload()"></i>
                <img id="roomImg" src="${room.roomImage}" alt="roomImg">
                <input type="file" id="imageUpload" accept="image/*" onchange="previewImage(event)">
            </div>
            <div class="info-div">
                <div class="form-group">
                    <label for="roomCount">객실 수</label>
                    <input type="number" class="form-control" id="roomCount" max="30" value="${room.roomCount}" oninput="maxNum(event)" required>
                </div>
                <div class="form-group">
                    <label for="maxDogs">최대 견수</label>
                    <input type="number" class="form-control" id="maxDogs" value="${room.maxDogs}" required>
                </div>
                <div class="form-group">
                    <label for="price">가격</label>
                    <input type="text" id="price" class="form-control" value="${room.price}" oninput="moneyFormatter(event)" required>
                </div>
            </div>
        </div>
        <div class="room-intro-div">
            <div class="form-group">
                <label for="intro">객실 소개글:</label>
                <textarea class="form-control" id="intro">${room.intro}</textarea>
            </div>
        </div>
        <button type="submit" class="button radius">수정하기</button>
        <div class="hidden">
            <input type="hidden" id="hotelId" value="${hotelId}">
        </div>
    </form>
</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/updateRoom.js"></script>
</html>