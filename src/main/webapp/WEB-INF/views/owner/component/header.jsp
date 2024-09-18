<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/header.css"/>
</head>
<body>
<div class="header">
    <security:authorize access="isAuthenticated()">
        <div class="logo-div">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="logo"
                 onclick="location.href = '/v1/owners'"/>
            <p><security:authentication property="principal.username"/> 사장님! 반갑습니다.</p>
        </div>
        <nav class="navbar">
            <div class="navbar-menu-container">
                <ul class="navbar-menu">
                    <li><a href="#" onclick="dropSubMenuLogin()">호텔 관리하기</a>
                        <ul class="dropdown-sub-menu" id="hotel-sub-menu-login">
                            <li><a href="${pageContext.request.contextPath}/v1/owners/hotel">호텔 등록</a></li>
                            <li><a href="#">예약 관리</a></li>
                            <li><a href="#">매출 통계</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="user">
                    <button type="button" onclick="location.href = '/v1/owners/logout'">로그아웃</button>
                </div>
            </div>
            <div class="menu-icon">
                <div class="menu-icon" id="menu-Toggle-login">
                    <a href="#" class="navbar-toggle" onclick="dropMenuLogin()"><i class="fas fa-bars"></i></a>
                </div>
                <ul class="dropdown-menu" id="drop-menu-login">
                    <li><a onclick="toggleDropSubMenuLogin()">호텔관리</a>
                        <ul class="dropdown-sub-menu" id="hotel-toggle-sub-menu-login">
                            <li><a href="${pageContext.request.contextPath}/v1/owners/hotel">호텔 등록</a></li>
                            <li><a href="#">예약 관리</a></li>
                            <li><a href="#">매출 통계</a></li>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/v1/owners/logout">로그아웃</a></li>
                </ul>
            </div>
        </nav>
    </security:authorize>
    <security:authorize access="!isAuthenticated()">
        <div class="logo-div">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="logo"
                 onclick="location.href = '/v1/owners'"/>
            <p>DDOG DOG OWNER PLACE</p>
        </div>
        <nav class="navbar">
            <div class="navbar-menu-container">
                <ul class="navbar-menu">
                    <li><a href="#" onclick="dropSubMenu()">호텔 관리하기</a>
                        <ul class="dropdown-sub-menu" id="hotel-sub-menu">
                            <li><a href="${pageContext.request.contextPath}/v1/owners/hotel">호텔 등록</a></li>
                            <li><a href="#">예약 관리</a></li>
                            <li><a href="#">매출 통계</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="user">
                    <button type="button" onclick="location.href = '/v1/owners/signup'">회원가입</button>
                    <button type="button" onclick="location.href = '/v1/owners/login'">로그인</button>
                </div>
            </div>
            <div class="menu-icon">
                <div class="menu-icon" id="menuToggle">
                    <a href="#" class="navbar-toggle" onclick="dropMenu()"><i class="fas fa-bars"></i></a>
                </div>
                <ul class="dropdown-menu" id="drop-menu">
                    <li><a onclick=toggleDropSubMenu()>호텔관리</a>
                        <ul class="dropdown-sub-menu" id="hotel-toggle-sub-menu">
                            <li><a href="${pageContext.request.contextPath}/v1/owners/hotel">호텔 등록</a></li>
                            <li><a href="#">예약 관리</a></li>
                            <li><a href="#">매출 통계</a></li>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/v1/owners/signup">회원가입</a></li>
                    <li><a href="${pageContext.request.contextPath}/v1/owners/login">로그인</a></li>
                </ul>

            </div>
        </nav>
    </security:authorize>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/header.js"></script>
</html>