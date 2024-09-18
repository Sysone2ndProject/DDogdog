<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:HOTEL</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/hotelRegister.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <h1>호텔등록</h1>
    <form id="hotelRegisterForm" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label for="business-name">상호명:</label>
            <input type="text" class="form-control" id="business-name">
        </div>
        <div class="form-group">
            <label for="business-number">사업자번호:</label>
            <input type="text" class="form-control" id="business-number">
        </div>
        <div class="form-group">
            <label for="phone-number">사업장연락처:</label>
            <input type="text" class="form-control" id="phone-number">
        </div>
        <div class="form-group">
            <label for="address">사업장주소:주소 찾기를 통해 입력해 주세요</label>
            <input type="text" class="form-control" id="address" placeholder="도로명 주소를 입력해 주세요" readonly>
            <button type="button" onclick="findAddress()">주소 찾기</button>
        </div>
        <div class="form-group">
            <label for="main-image">호텔 대표 섬네일</label>
            <input type="file" class="form-control-file" id="main-image" accept="image/*" required onchange="previewThumnail(event)">
            <div id="image-preview-div">
                <img id="thumbnail-preview">
            </div>
        </div>
        <div class="form-group">
            <label for="intro">호텔 소개글:</label>
            <textarea class="form-control" id="intro"></textarea>
        </div>
        <button type="submit" class="btn btn-success">등록하기</button>
        <div class="address-hidden">
            <input type="hidden" id="sido">
            <input type="hidden" id="sigungu">
            <input type="hidden" id="dong">
        </div>
    </form>
</div>


<div class="footer-box">
    <jsp:include page="component/footer.jsp"></jsp:include>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/hotelRegister.js"></script>
</html>