<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:Room 관리</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/roomList.css"/>
</head>
<body>

<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="room-list-title">
        <div class="hotel-title">
            <span id="hotelId">${hotelId}</span>
            <span id="hotelName">${hotelName}</span>
        </div>
        <button type="button" class="gray-btn radius" onclick="location.href = '/v1/owners/rooms/form/${hotelId}'"> 객실 등록하기</button>
    </div>

    <div class="room-card-list">
        <c:forEach var="room" items="${rooms}">
            <div class="room-card shadow">
                <div class="room-img">
                    <img src="${room.roomImage}" alt="Room-Img">
                </div>
                <div class="room-info">
                    <p id="roomGrade">${room.grade}</p>
                    <p>룸 개수: ${room.roomCount}</p>
                    <p>가격: <fmt:formatNumber value="${room.price}" type="number"
                                             groupingUsed="true"/></p>
                    <p>최대 견: ${room.maxDogs}</p>
                    <p>소개글: ${room.intro}</p>
                </div>
                <div class="room-manage">
                    <button type="button" class="gray-btn radius"
                            onclick="location.href = '/v1/owners/rooms/updateform/${room.grade}?hotelId=${hotelId}'"> 객실 수정
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>

</html>
