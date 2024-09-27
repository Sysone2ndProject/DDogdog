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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<!-- 로그인 후 권한 확인 테스트용 입니다. -->
<div class="container">
    <div>
        <p><security:authentication property="principal.username"/> 님 로그인 완료</p>
        <button type="button" class="btn btn-primary" onclick="location.href = '/v1/owners'">메인페이지로가기</button>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>
</body>

</html>