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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>

<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="title">
        <p>호텔등록</p>
    </div>
    <div class="form radius shadow">
        <form id="hotelRegisterForm" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="businessName">상호명</label>
                <input type="text" class="form-control" id="businessName">
            </div>
            <div class="form-group">
                <label for="businessNumber">사업자번호</label>
                <input type="text" class="form-control" id="businessNumber" oninput="businessNumberCheck()"내>
            </div>
            <div class="form-group">
                <label for="phoneNumber">사업장연락처</label>
                <input type="text" class="form-control" id="phoneNumber" maxlength="11" oninput="phoneNumberCheck()"내>
            </div>
            <div class="form-group address">
                <label for="address">사업장주소 <span>(주소 찾기를 통해 입력해 주세요)</span></label>
                <input type="text" class="form-control" id="address" placeholder="도로명 주소를 입력해 주세요" readonly>
                <button type="button" class="address-btn" onclick="findAddress()">주소 찾기</button>
            </div>
            <div class="form-group">
                <label for="mainImage">호텔 대표 섬네일</label>
                <input type="file" class="form-control-file" id="mainImage" accept="image/*" required onchange="previewThumnail(event)">
                <div id="imagePreviewDiv">
                    <img id="thumbnailPreview">
                </div>
            </div>
            <div class="form-group intro">
                <label for="intro">호텔 소개글:</label>
                <textarea class="form-control" id="intro"></textarea>
            </div>
            <button type="submit" class="button radius">등록하기</button>
            <div class="address-hidden">
                <input type="hidden" id="sido">
                <input type="hidden" id="sigungu">
                <input type="hidden" id="dong">
            </div>
        </form>
    </div>
</div>


<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/hotelRegister.js"></script>
</html>