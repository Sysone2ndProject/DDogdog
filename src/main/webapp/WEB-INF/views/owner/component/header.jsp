<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/header.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<div class="header">
    <div class="header-content">
        <img class="logo" src="${pageContext.request.contextPath}/img/owner/logo_nobg.svg"
             onclick="location.href='/v1/owners'" alt="logo"/>

        <!-- 비로그인 시: 인증되지 않은 사용자라면 -->
        <sec:authorize access="isAnonymous()">
            <div class="nav-list">
                <p class="nav-item" onclick="needLogin()">Management</p>
                <p class="nav-item" onclick="needLogin()">Reservation</p>
                <p class="nav-item" onclick="needLogin()">Analyze</p>
            </div>
            <div class="login-btn">
                <button class="button radius" onclick="location.href='/v1/owners/login'">로그인</button>
                <button class="button radius" onclick="location.href='/v1/owners/signup'">회원가입</button>
            </div>
        </sec:authorize>

        <!-- 로그인 시 -->
        <sec:authorize access="isAuthenticated()">
            <div class="nav-list">
                <p class="nav-item" onclick="location.href='/v1/owners/hotels'">Management</p>
                <p class="nav-item" onclick="location.href='/v1/owners/reservations/form'">Reservation</p>
                <p class="nav-item" onclick="location.href='/v1/owners/reservations/statistics'">Analyze</p>
            </div>
            <div class="login-btn">
                <button class="button radius" onclick="location.href='/v1/logout'">로그아웃</button>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/header.js"></script>
</html>
