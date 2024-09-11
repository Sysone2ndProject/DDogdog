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
        <button type="button" id="signUp" onclick="getSignUpForm()">회원가입</button>
    </div>
    <div>
        <button type="button" id="login" data-bs-toggle="modal" data-bs-target="#login-modal">로그인</button>
    </div>

</div>

<div class="modal" id="login-modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">로그인해주세요</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/v1/owners/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="id">아이디:</label>
                        <input type="text" class="form-control" id="id" name="id">
                    </div>
                    <div class="form-group">
                        <label for="password">password:</label>
                        <input type="text" class="form-control" id="password" name="password">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="location.href='/v1/owners/signup'">회원가입</button>
                    <button type="submit" class="btn btn-primary">로그인</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/owners/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</html>