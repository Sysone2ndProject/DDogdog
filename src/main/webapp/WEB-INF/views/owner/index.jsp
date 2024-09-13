<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- 테스트용 입니다. -->
<div class="container">
    <div>
        <h1>OWNER 메인페이지</h1>
    </div>
    <div>
        <button type="button" id="sign-up" onclick="location.href = '/v1/owners/signup'">회원가입</button>
    </div>
    <div>
        <button type="button" id="login" onclick="location.href = '/v1/owners/login'">로그인</button>
    </div>
    <div>
        <button type="button" id="user-page" onclick="location.href = '/v1/owners/user'">유저페이지</button>
    </div>
</div>
</body>
</html>