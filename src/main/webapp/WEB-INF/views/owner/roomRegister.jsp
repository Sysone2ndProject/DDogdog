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

<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <input type="hidden" id="hotelIdHidden" value="${hotelId}">
    <h1>객실등록 </h1>
    <form id="roomRegisterForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="roomPhoto">객실 사진</label>
            <input type="file" id="roomPhoto" class="form-control" accept="image/*" required onchange="previewRoomImg(event)">
            <div class="room-img-preview">
                <img id="roomImgReview">
            </div>
        </div>
        <div class="form-group">
            <label for="roomGrade">객실 등급</label>
            <select id="roomGrade" class="form-control">
                <c:forEach var="grade" items="${grades}">
                    <option value="${grade.name()}">${grade.detail}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="roomCount">객실 갯수</label>
            <input type="number" id="roomCount" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="roomPrice">객실 가격</label>
            <input type="number" id="roomPrice" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="maxDogs">최대 견수</label>
            <input type="number" id="maxDogs" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="intro">룸 소개글:</label>
            <textarea class="form-control" id="intro"></textarea>
        </div>
        <button type="submit" class="button radius">등록</button>
    </form>
</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/roomRegister.js"></script>
</html>