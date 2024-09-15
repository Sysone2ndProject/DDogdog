<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hotel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<!-- 테스트용 입니다. -->
<div class="container mt-5">
    <h2>로그인</h2>

    <c:if test="${param.error eq 'true'}">
        <div class="alert alert-danger" role="alert">
            로그인에 실패했습니다. 아이디와 비밀번호를 확인해 주세요.
        </div>
    </c:if>

    <c:if test="${param.alert eq 'true'}">
        <div class="alert alert-warning" role="alert">
            회원가입 또는 로그인이 필요합니다.
        </div>
    </c:if>

    <c:if test="${param.logout eq 'true'}">
        <div class="alert alert-success" role="alert">
            로그아웃되었습니다.
        </div>
    </c:if>

    <form action="/loginPro" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group mb-3">
            <label for="id">아이디:</label>
            <input type="text" class="form-control" id="id" name="id" required>
        </div>
        <div class="form-group mb-3">
            <label for="password">비밀번호:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
    </form>

    <div class="mt-3">
        <button type="button" class="btn btn-primary" onclick="location.href = '/v1/owners/signup'">회원가입</button>
    </div>
</div>

</body>
</html>