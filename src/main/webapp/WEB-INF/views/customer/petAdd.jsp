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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers/petAdd.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- Axios 및 JavaScript 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 추가 -->

</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <div class="pet-add">

        <!-- 입력 폼 -->
        <h2>펫 등록</h2>
        <div id="left">
            <div id="imageInsert">
                <div id="imagePreview">
                    <!-- 미리보기 이미지가 여기에 표시됩니다. -->
                </div>
            </div>
            <label class="custom-file-upload">
                펫 사진 등록
                <input type="file" id="image" name="image" class="file-input"
                       onchange="handleFileSelect(event)">
            </label>
        </div>

        <form id="petForm" onsubmit="submitForm(event)" action="#" method="post"
              enctype="multipart/form-data">
            <label for="name">이름:</label><br>
            <input type="text" id="name" name="name" required><br><br>

            <label for="age">나이:</label><br>
            <input type="text" id="age" name="age" required><br><br>

            <label for="gender">성별:</label><br>
            <select id="gender" name="gender" required>
                <option value="">선택하세요</option>
                <option value="male">남자</option>
                <option value="female">여자</option>
            </select><br><br>

            <label for="weight">몸무게:</label><br>
            <input type="number" id="weight" name="weight"><br><br>

            <div id="species">견종</div>
            <!-- SweetAlert2로 변경 -->
            <button type="button" class="btn btn-primary" onclick="openBreedSelectionModal()">
                견종 선택
            </button>
            <br><br>

            <label for="info">추가 정보:</label><br>
            <textarea id="info" name="info" required></textarea><br><br>

            <button type="submit">등록</button>
        </form>
    </div>
</div>

<script src="/js/customers/petAdd.js"></script>
</script>

</body>
</html>
