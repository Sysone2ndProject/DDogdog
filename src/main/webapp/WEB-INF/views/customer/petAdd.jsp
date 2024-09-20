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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers/petAdd.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Axios 및 JavaScript 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
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
        <input type="text" id="gender" name="gender" required><br><br>

        <label for="weight">몸무게:</label><br>
        <input type="text" id="weight" name="weight"><br><br>

        <div id="species">견종</div>
        <!-- 견종 버튼을 모달로 변경 -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#breedModal">
            견종 선택
        </button>
        <br><br>

        <label for="info">추가 정보:</label><br>
        <textarea id="info" name="info" required></textarea><br><br>

        <button type="submit">등록</button>
    </form>
</div>

<!-- 모달 창 -->
<div class="modal fade" id="breedModal" tabindex="-1" role="dialog"
     aria-labelledby="breedModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="breedModalLabel">견종 선택</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 검색 입력 필드와 버튼 추가 -->
                <div class="input-group mb-3">
                    <input type="text" id="searchBreed" class="form-control" placeholder="견종 검색">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button"
                                onclick="searchBreed()">검색
                        </button>
                    </div>
                </div>
                <ul id="breedList" class="list-group">
                    <!-- 견종 리스트가 여기에 비동기로 추가됩니다. -->
                </ul>
                <div id="noResults" class="d-none">
                    <p>검색 결과가 없으면
                        <button type="button" class="btn btn-link"
                                onclick="showBreedRegistrationForm()">견종 등록 버튼을 눌러 등록해주세요
                        </button>
                    </p>
                </div>
                <!-- 견종 등록 폼이 추가될 부분 -->
                <div id="registrationForm" class="d-none">
                    <h5>견종 등록</h5>
                    <form id="breedRegistrationForm">
                        <div class="form-group">
                            <label for="newBreedName"></label>
                            <input type="text" id="newBreedName" class="form-control"
                                   placeholder="견종 이름">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="registerBreed()">등록
                        </button>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<script src="/js/customers/petAdd.js"></script>
</body>
</html>
