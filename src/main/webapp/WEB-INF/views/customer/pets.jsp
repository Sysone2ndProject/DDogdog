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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers/pets.css">

</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <h2>펫 목록</h2>
    <ul>
        <c:forEach var="pet" items="${pets}">
            <li class="pet-item" data-id="${pet.id}">
                <img class="petImage" src="${pet.petImage}" alt="${pet.name}">
                <div class="pet-info">
                    <span>${pet.name}</span>
                    <span>${pet.age}살</span>
                </div>
            </li>
        </c:forEach>
    </ul>
    <div class="add-button" onclick="location.href='/v1/customers/pets/add'">
        +
    </div>
</div>
    <script src="/js/customers/pets.js"></script>
</body>
</html>
