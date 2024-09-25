<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:HOTEL's Room</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/roomRegister.css"/>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <input type="hidden" id="hotel-id-hidden" value="${hotelId}">
    <h1>객실등록 </h1>
    <form id="roomRegisterForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="room-photo">객실 사진</label>
            <input type="file" id="room-photo" class="form-control" accept="image/*" required onchange="previewRoomImg(event)">
            <div class = "room-img-preview">
                <img id="room-img-review">
            </div>
        </div>
        <div class="form-group">
            <label for="room-grade">객실 등급</label>
            <select id="room-grade" class="form-control">
                <c:forEach var="grade" items="${grades}">
                    <option value="${grade.name()}">${grade.detail}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="room-count">객실 갯수</label>
            <input type="number" id="room-count" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="room-price">객실 가격</label>
            <input type="number" id="room-price" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="max-dogs">최대 견수</label>
            <input type="number" id="max-dogs" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="intro">룸 소개글:</label>
            <textarea class="form-control" id="intro"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
    </form>
</div>


<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/roomRegister.js"></script>
</html>