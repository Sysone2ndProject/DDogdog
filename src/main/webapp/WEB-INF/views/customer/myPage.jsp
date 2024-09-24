<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>마이페이지</title>
    <script>
      let addressId = ${CustomerInfo.addressId};
      let customerId =
      <sec:authentication property="principal.username"/>;
    </script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
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
                <canvas id="canvas" width="300" height="300"></canvas>
                <br>
                <div class="userName">
                    <div class="userImage"></div>
                    <h3> ${CustomerInfo.name}</h3> 고객님 환영합니다!
                </div>
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
                <button id="reservationButton">똑독 이용 내역</button>
            </div>
        </div>
        <div class="card"> <!-- 두 번째 카드 -->
            <h3>주소 : </h3> <a>수정하기</a>
                <p id="address-display"></p>
            <div id="map"></div>
        </div>
    </div>

    <div class="right-column">
        <div class="card"> <!-- 세 번째 카드 -->
            펫 정보    <button> 추가 </button>
        </div>
    </div>

<%--    <button id="dogInfoButton">내 강아지 정보</button>--%>

</div>

<script type="importmap">
    {
        "imports": {
          "three": "https://unpkg.com/three@0.141.0/build/three.module.js",
          "GLTFLoader": "https://unpkg.com/three@0.141.0/examples/jsm/loaders/GLTFLoader.js",
          "OrbitControls": "https://unpkg.com/three@0.141.0/examples/jsm/controls/OrbitControls.js"
        }
    }
</script>
<script src="/js/customers/shiba.js" type="module"></script>
</body>
</html>
