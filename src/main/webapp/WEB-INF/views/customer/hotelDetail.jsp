<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
</head>
<body>
<h1>${hotelDetail.hotel.businessName}</h1>
<p>${hotelDetail.address.fullAddress}</p>
<hr>
<c:forEach var="room" items="${hotelDetail.rooms}">
    <p>객실 등급: ${room.grade}</p>
    <p>남은 수: ${room.count}</p>
    <p>가격: ${room.price}</p>
    <hr>
</c:forEach>
<form id="reservationForm">
    <label for="startDate">체크인</label>
    <input id="startDate" type="date" onchange="setEndDate()">
    <label for="endDate">체크아웃</label>
    <input id="endDate" type="date">
    <c:forEach var="room" varStatus="status" items="${hotelDetail.rooms}">
        <label for="count${status.index}" class="grade${status.index}">${room.grade}</label>
        <input id="count${status.index}" type="number" min="0" value="0"
               onchange="calcCountAndPrice(${status.index})">
    </c:forEach>
    <p>총계: </p>
    <p id="totalPrice">0</p>
    <p>총 개수: </p>
    <p id="totalCount">0</p>
    <button type="submit">예약하기</button>
</form>
</body>
<script>
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
<script src="/js/customers/hotelDetail.js"></script>
<script src="/js/customers/dateHandler.js"></script>
</html>
