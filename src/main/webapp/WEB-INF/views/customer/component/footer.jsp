<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Footer</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/footer.css"/>
</head>
<body>
<div class="footer">
    <div class="footer-content">
        <div class="footer-info">
            <div class="pjt">
                <img class="footer-logo" src="${pageContext.request.contextPath}/img/logo_nobg.png">
                <p class="subtext">Travel helps companies manage pets easily.</p>
            </div>
            <div class="team">
                <p class="title">Company</p>
                <p class="member">김수지</p>
                <p class="member">김태연</p>
                <p class="member">이준희</p>
            </div>
            <div class="team">
                <p class="title">Search</p>
            </div>
        </div>
        <div>
            <p class="subtext">Copyright @ 2024 SYSONE. All Rights Reserved.</p>
        </div>

    </div>
                <img class="footer-img" src="${pageContext.request.contextPath}/img/footer_img.svg">
</div>
</body>
</html>
