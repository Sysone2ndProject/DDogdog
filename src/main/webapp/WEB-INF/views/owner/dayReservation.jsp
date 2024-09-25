<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER: 일 예약 현황</title>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <h1>호텔 일별 예약 현황 확인</h1>
    <div class="calendar">
        <c:forEach var="reservation" items="${reservations}">
            <div class="reservation-card">
                <p>${reservation.grade}</p>
                <p>${reservation.roomCount}</p>
                <p>${reservation.customerName}</p>
            </div>
        </c:forEach>
    </div>
</div>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
</html>