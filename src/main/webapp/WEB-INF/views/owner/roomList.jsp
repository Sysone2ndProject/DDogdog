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
    <title>OWNER:Room 관리</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/roomList.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="component/header.jsp"></jsp:include>

<!-- 테스트용 입니다. -->
<div class="container">
    <div class="room-list-title">
        <div class="hotel-title">
            <span id="hotel-id">${hotelId}</span>
            <span id="hotel-name">${hotelName}</span>
        </div>
        <button type="button" onclick="location.href = '/v1/owners/rooms/form/${hotelId}'"> 객실 등록하기</button>
    </div>


    <c:forEach var="room" items="${rooms}">
        <div class="room-card">
            <div class="room-img">
                <img src="${room.roomImage}" alt="Room-Img">
            </div>
            <div class="room-info">
                <p id="Room-grade">${room.grade}</p>
                <p>룸 개수: ${room.roomCount}</p>
                <p>가격: ${room.price}</p>
                <p>최대 견: ${room.maxDogs}</p>
                <p>소개글: ${room.intro}</p>
            </div>
            <div class="room-manage">
                <button type="button" onclick="location.href = '/v1/owners/rooms/updateform/${room.grade}?hotelId=${hotelId}'"> 객실 수정
                </button>
                <button type="button"> 객실 삭제</button>
            </div>
        </div>

    </c:forEach>
</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/roomList.js"></script>
</html>