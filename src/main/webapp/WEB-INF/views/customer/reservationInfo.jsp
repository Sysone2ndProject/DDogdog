<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>예약정보</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/reservationInfo.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <!-- Axios -->
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <div class="reservation">
        <p class="page-title">예약 내역 확인</p>
        <div class="ticket-list">
            <c:forEach var="reservation" items="${ReservationInfos.content}">
                <div class="ticket radius shadow">
                    <c:if test="${reservation.endDate lt today}">
                        <img class="stamp" src="${pageContext.request.contextPath}/img/stamp.svg"/>
                    </c:if>
                    <img class="hotel-img" src="${reservation.mainImage}"
                         alt="${reservation.businessName} 이미지"/>
                    <div class="reservation-details">
                        <p class="hotel-name">${reservation.businessName}</p>
                        <p>${reservation.intro}</p>
                        <p class="hotel-loc">${reservation.fullAddress}</p>
                        <p class="text-info">${reservation.startDate} - ${reservation.endDate}</p>
                    </div>
                    <div class="reservation-summary">
                        <div class="text-info">
                            예약일자: ${reservation.createDate}
                        </div>
                        <p class="total-count">총 ${reservation.count}개</p>
                        <div class="price"><fmt:formatNumber value="${reservation.price}"
                                                             type="number"
                                                             groupingUsed="true"/> 원
                        </div>
                        <div class="buttons">
                            <button class="view-details radius"
                                    onclick="viewDetails(${reservation.id})">
                                자세히 보기
                            </button>
                            <c:if test="${reservation.canceled}">
                                <button class="completed radius">취소 완료</button>
                            </c:if>
                            <c:if test="${!reservation.canceled}">
                                <c:choose>
                                    <c:when test="${reservation.endDate lt today}">
                                        <button class="completed radius">사용 완료</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="cancel radius"
                                                onclick="cancelReservation(${reservation.id})">예약 취소
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}&size=${size}" class="pagination-btn">이전</a>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="?page=${i}&size=${size}"
                   class="pagination-btn ${i == currentPage ? 'active' : 'inactive'}">${i}</a>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}&size=${size}" class="pagination-btn">다음</a>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>
</body>
<script src="/js/customers/reservationInfo.js"></script>
</html>
