<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>펫 등록</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/petAdd.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <!-- 입력 폼 -->
    <h2>펫 정보</h2>
    <div id="left">
        <div id="imageInsert">
            <div id="imagePreview">
                <!-- 미리보기 이미지가 여기에 표시됩니다. -->
                <img src="${pet.petImage}" style="width:180px; height:180px;">
            </div>
        </div>
    </div>

    <form id="petForm" action="#" method="post" enctype="multipart/form-data">
        <label for="name">이름:</label><br>
        <input type="text" id="name" name="name" value="${pet.name}" required><br><br>

        <label for="age">나이:</label><br>
        <input type="text" id="age" name="age" value="${pet.age}" required><br><br>

        <label for="gender">성별:</label><br>
        <input type="text" id="gender" name="gender" value="${pet.gender}" required><br><br>

        <label for="weight">몸무게:</label><br>
        <input type="text" id="weight" name="weight" value="${pet.weight}"><br><br>

        <div id="species">${pet.speciesId}</div>


        <label for="info">추가 정보:</label><br>
        <textarea id="info" name="info" required>${pet.info}</textarea><br><br>

        <button type="submit">수정</button>
    </form>
</div>

<!-- Axios 및 JavaScript 추가 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="/js/customers/petAdd.js"></script>

</body>
</html>
