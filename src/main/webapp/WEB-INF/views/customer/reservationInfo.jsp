<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>예약정보</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers/reservationInfo.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <!-- Axios -->
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<h1>예약 정보</h1>

    <c:forEach var="reservation" items="${ReservationInfos}">
        <div class="ticket">
            <div class="image-container">
                <img src="${reservation.mainImage}" alt="${reservation.businessName} 이미지" />
            </div>

            <div class="reservation-details">
                <h2>${reservation.businessName}</h2>
                <p>${reservation.intro}</p>
                <p>${reservation.fullAddress}</p>
                <div class="reservation-footer">
                    ${reservation.startDate} - ${reservation.endDate}<br>
                    ${reservation.fullAddress}
                </div>
            </div>

            <!-- 세로 실선 -->
            <div class="vertical-line"></div>

            <!-- 오른쪽 예약 요약 및 버튼 -->
            <div class="reservation-summary">
                <div class="reservation-footer">
                    예약일자: ${reservation.createDate}
                </div>
                <p class="total-count">총 ${reservation.count}개</p>
                <div class="price">${reservation.price} 원</div>
                <div class="buttons">
                    <button class="view-details" onclick="viewDetails(${reservation.id})">자세히 보기</button>
                    <c:if test="${reservation.canceled}">
                        <button class="completed">취소 완료</button>
                    </c:if>
                    <c:if test="${!reservation.canceled}">
                        <button class="cancel" onclick="cancelReservation(${reservation.id})">예약 취소</button>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
<script src="/js/customers/reservationInfo.js"></script>
</body>
</html>
