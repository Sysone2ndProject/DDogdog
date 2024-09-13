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
    <h1>About us</h1>
    <p>© 2024 Project 어서오시개</p>
    <img class="icon" src="${pageContext.request.contextPath}/img/github_logo.svg"
         alt="github-logo"/>
    <div class="icon-maker"> 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/dave-gandy"
                                        title="Dave Gandy"> Dave Gandy </a> from <a
            href="https://www.flaticon.com/kr/" title="Flaticon">www.flaticon.com'</a></div>
</div>
</body>
</html>
