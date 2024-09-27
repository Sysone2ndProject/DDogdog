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
    <!-- Axios 및 JavaScript 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 추가 -->

</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <div class="pet-add">

        <!-- 입력 폼 -->
        <div class="left">
            <div id="imageInsert">
                <div id="imagePreview">
                    <!-- 미리보기 이미지가 여기에 표시됩니다. -->
                </div>
            </div>
            <label class="custom-file-upload">
                사진 등록
                <input type="file" id="image" name="image" class="file-input"
                       onchange="handleFileSelect(event)">
            </label>

            <div class="species-input">
                <div class="dog-species">견종 :</div>  <div id="species" class="species-underline"></div>
                <button type="button" class="button-custom" onclick="openBreedSelectionModal()">
                    견종 선택
                </button>
            </div>
            <br><br>
        </div>
        <div class="middle">
        </div>
        <form id="petForm" onsubmit="submitForm(event)" action="#" method="post"
              enctype="multipart/form-data">
            <div class="form-group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" class="input-underline" required><br><br>
            </div>
            <div class="form-group">
            <label for="age">나이:</label>
            <input type="text" id="age" name="age" class="input-underline" required><br><br>
            </div>

            <div class="form-group">
            <label for="gender">성별:</label>
            <select id="gender" name="gender" class="input-underline" required>
                <option>선택해주세요</option>
                <option value="남자">남자</option>
                <option value="여자">여자</option>
            </select><br><br>
            </div>

            <div class="form-group">
            <label for="weight">몸무게:</label>
            <input type="number" id="weight" name="weight" class="input-underline"><br><br>
            </div>


            <label for="info">추가 정보</label><br>
            <div class="textarea-container">
                <textarea id="info" name="info" required></textarea><br><br>
            </div>
            <div class="end">
             <button class="button-custom" type="submit">등록</button>
            </div>
        </form>
    </div>
</div>
<script src="/js/customers/petAdd.js"></script>
</script>
<jsp:include page="component/footer.jsp"/>
</body>
</html>
