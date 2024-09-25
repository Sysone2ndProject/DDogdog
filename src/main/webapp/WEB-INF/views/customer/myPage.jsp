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
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${apiKey}&libraries=services"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/myPage.css">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
<div class="main">
    <div class="navbar">
        <div class="navbar-card now">
            마이페이지
        </div>
        <div class="navbar-card" onclick="reservationButton()">
            똑똑 이용 내역
        </div>
        <div class="navbar-card" onclick="showLocation()">
            개인 정보 수정
        </div>
    </div>
    <div class="left-column">
        <div class="card"> <!-- 첫 번째 카드 -->
            <div class="userInfo">
                <canvas id="canvas"></canvas>
                <br>
                <div class="userName">
                </div>
            </div>
            <div class="userDetail">
                <br>
                <div class="chip">
                <div class="userImage"></div>
                <h3> ${CustomerInfo.name}</h3> 고객님 환영합니다!
                </div>
                <div class="chip">
                    가입 이메일 : ${CustomerInfo.email}
                </div>
                <div class="chip">
                    연령대 : ${CustomerInfo.ageRange}
                </div>
                <div class="chip">
                    성별 : ${CustomerInfo.gender}자
                </div>
                <div class="chip">
                    <div id="addressDisplay"></div>
                </div>
            </div>
        </div>
        <div class="card-map">
            <div class="reservation-stats">
                <div class="stats">총 예약 횟수
                    <div class="reservations total"></div>
                </div>
                <div class="stats">이용중
                    <div class="reservations current"></div>
                </div>
                <div class="stats">이용 완료
                    <div class="reservations past"></div>
                </div>
                <div class="stats">잔여 예약
                    <div class="reservations future"></div>
                </div>
            </div>
            <!-- 두 번째 카드 -->
<%--            <div class="map-info">--%>
<%--&lt;%&ndash;                <p id="address-display"></p>&ndash;%&gt;--%>
<%--            </div>--%>
<%--            <div id="map"></div>--%>

        </div>
    </div>

    <div class="right-column">
        <div class="pet-card"> <!-- 세 번째 카드 -->
            펫 정보
            <button onclick="petAddButton()"> 추가 </button>
        </div>

    </div>
</div>
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
<jsp:include page="component/footer.jsp"/>
</body>
</html>
