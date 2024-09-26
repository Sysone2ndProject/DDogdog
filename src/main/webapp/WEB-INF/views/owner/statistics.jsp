<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/statistics.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>

<div class="container">
    <div class="select">
        <div class="hotel">
            <c:forEach var="hotel" items="${hotels}">
                <button><input type="hidden" value="${hotel.hotelId}">${hotel.businessName}</button>
            </c:forEach>
        </div>

        <div class="select-year">
            <label for="year">연도 선택 : </label>
            <select id="year">
                <option value="${year + 1}">${year + 1}</option>
                <option value="${year}" selected>${year}</option>
                <option value="${year - 1}">${year - 1}</option>
                <option value="${year - 2}">${year - 2}</option>
                <option value="${year - 3}">${year - 3}</option>
                <option value="${year - 4}">${year - 4}</option>
                <option value="${year - 5}">${year - 5}</option>
            </select>
        </div>

    </div>

    <div class="grade-statics">
        <canvas id="gradeIncome"></canvas>
        <canvas id="gradeRoomCount"></canvas>
    </div>

    <div class="year-statics">
        <canvas id="monthIncome"></canvas>
    </div>
</div>

<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/statistics.js"></script>
</html>