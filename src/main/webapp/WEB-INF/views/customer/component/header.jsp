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
</head>
<body>
<div class="header">
    <a href="/v1/customers">
        <img class="logo" src="${pageContext.request.contextPath}/img/logo_nobg.png" alt="logo"/>
    </a>

    <!-- 비로그인 시: 인증되지 않은 사용자라면 -->
    <sec:authorize access="isAnonymous()">
            <div class="login">
                <a href="/oauth2/authorization/kakao">
                    <img src="${pageContext.request.contextPath}/img/kakao_login_medium.png" alt="카카오 로그인" style="cursor: pointer;">
                </a>
            </div>
    </sec:authorize>

    <!-- 로그인 시 -->
    <sec:authorize access="isAuthenticated()">
        <div class="login">
            환영합니다 <sec:authentication property="principal.name" /> 고객님
            <button onclick="location.href='/v1/customers/myPage';">마이페이지</button>
            <button>로그아웃</button>
        </div>
    </sec:authorize>

</div>
</body>
</html>
