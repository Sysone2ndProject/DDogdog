<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>카카오 로그인</title>
</head>
<body>
<!-- 카카오 로그인 이미지 버튼 -->
<img src="${pageContext.request.contextPath}/img/DDogdogLOGO.png" alt="로고" style="cursor: pointer;">
<a href="/oauth2/authorization/kakao">
    <img src="${pageContext.request.contextPath}/img/kakao_login_large_wide.png" alt="카카오 로그인" style="cursor: pointer;">
</a>
</body>
</html>