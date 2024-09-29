<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Header</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/header.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined" rel="stylesheet">
</head>
<body>
<div class="header">
    <div class="header-content">
        <img class="logo" src="${pageContext.request.contextPath}/img/logo_nobg.png"
             onclick="location.href='/v1/customers'" alt="logo"/>

        <div class="nav-list">
            <p class="nav-item" onclick="location.href='/v1/customers'">Home</p>
            <p class="nav-item" onclick="location.href='/v1/customers/member'">MyPage</p>
            <p class="nav-item" onclick="location.href=''">About</p>
        </div>

        <!-- 비로그인 시: 인증되지 않은 사용자라면 -->
        <sec:authorize access="isAnonymous()">
            <div class="login-btn">
                <button class="button radius" onclick="location.href='/oauth2/authorization/kakao'">
                    로그인/회원가입
                </button>
            </div>
        </sec:authorize>

        <!-- 로그인 시 -->
        <sec:authorize access="isAuthenticated()">
            <div class="login">
            <span class="user-name">
            환영합니다 <sec:authentication property="principal.name"/> 고객님
            </span>
                <span class="material-icons" onclick="location.href='/v1/logout'">
                logout
            </span>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/customers/header.js"></script>
</html>
