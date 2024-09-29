<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>펫 정보 확인 및 수정</title>
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
                    <img src="${pet.petImage}" class="image-preview">
                </div>
            </div>
            <label class="custom-file-upload">
                사진 변경
                <input type="file" id="image" name="image" class="file-input"
                       onchange="handleFileSelect(event)">
            </label>
            <div class="species-input">
                <div class="dog-species">견종 : </div> <div id="species" class="species-underline" >${pet.species}</div>
                <button type="button" class="button-custom" onclick="openBreedSelectionModal()">
                    견종 변경
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
                <input type="text" id="name" name="name" value="${pet.name}" class="input-underline"required><br><br>
            </div>
            <div class="form-group">
                <label for="age">나이:</label>
                <input type="text" id="age" name="age" value="${pet.age}" class="input-underline"required><br><br>
            </div>

            <div class="form-group">
                <label for="gender">성별:</label>
                <select id="gender" name="gender" value="${pet.gender}" class="input-underline"required>
                    <option>선택해주세요</option>
                    <option value="남자" ${pet.gender == '남자' ? 'selected' : ''}>남자</option>
                    <option value="여자" ${pet.gender == '여자' ? 'selected' : ''}>여자</option>
                </select><br><br>
            </div>

            <div class="form-group">
                <label for="weight">몸무게:</label>
                <input type="number" id="weight" name="weight" value="${pet.weight}" class="input-underline"><br><br>
            </div>


            <label for="info">추가 정보</label><br>
            <div class="textarea-container">
                <textarea id="info" name="info" required>${pet.info}</textarea><br><br>
            </div>
            <div class="end">
                <button class="button-custom" type="submit">수정</button>
                <button class="button-custom check" onclick="navigateToPage()">확인</button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="component/footer.jsp"/>
</body>
<script src="/js/customers/petDetails.js"></script>
<script> let petId =${pet.id}</script>
<script>let defaultPetSpeciesId =${pet.speciesId}</script>
<script> let existingImageUrl = '${pet.petImage}'</script>
</html>
