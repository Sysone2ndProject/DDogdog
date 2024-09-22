<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>검색</title>
    <script>
        let addressId = ${CustomerInfo.addressId};
    </script>
    <script src="/js/customers/myPage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${apiKey}&libraries=services"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/myPage.css">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">

    <div class="left-column">
        <div class="card"> <!-- 첫 번째 카드 -->
            <div class="userInfo">
                <div class="userImage"></div>
                <h3> ${CustomerInfo.name}</h3> 고객님 환영합니다!
            </div>
            <div class="userDetail">
                <br>
                <div class="chip">
                    가입 이메일 : ${CustomerInfo.email}
                </div>
                <div class="chip">
                    연령대 : ${CustomerInfo.ageRange}
                </div>
                <div class="chip">
                    성별 : ${CustomerInfo.gender}
                </div>
            </div>
        </div>
        <div class="card"> <!-- 두 번째 카드 -->
            <h3>주소 : <p id="address-display"></p></h3>
            <div id="map"></div>
        </div>
    </div>

    <div class="right-column">
        <div class="card"> <!-- 세 번째 카드 -->
            <a href="/v1/customers/pets"> 내 강아지 정보
                <a href="/v1/customers/reservation">
                    <button> 똑독 이용 내역</button>
                </a>
                <button> 강아지랑 놀기</button>
        </div>
    </div>
</div>


</body>
</html>
