<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>검색</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/myPage.css">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <h2>Pet Info</h2>
    <!-- 입력 폼 -->
    <form action="submitForm" method="post">
        <label for="name">이름:</label><br>
        <input type="text" id="name" name="name" required><br><br>

        <label for="age">나이:</label><br>
        <input type="text" id="age" name="age" required><br><br>

        <label for="gender">성별:</label><br>
        <input type="text" id="gender" name="gender" required><br><br>

        <label for="info">추가 정보:</label><br>
        <input type="text" id="info" name="info" required><br><br>

        <label for="image">Upload Image:</label>
        <input type="file" id="image" name="image" required/>
        <button type="submit">Upload</button>

        <input type="submit" value="Submit">
    </form>
</div>

<script> let addressId = ${CustomerInfo.addressId};</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/js/customers/myPage/myPage.js"></script>


</body>
</html>
