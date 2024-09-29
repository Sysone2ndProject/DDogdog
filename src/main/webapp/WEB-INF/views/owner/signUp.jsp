<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OWNER:회원가입</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/owners/signUp.css"/>
    <script src="https://kit.fontawesome.com/a08faffd77.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>

<div class="container">
    <div class="signup-title">
        <p>회원가입</p>
    </div>

    <form id="signUpForm" action="${pageContext.request.contextPath}/v1/owners/signup" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-group id-div">
            <i class="fa-solid fa-user"></i>
            <input type="text" class="form-control" id="id" name="id" placeholder="아이디" required>
            <button class="check-btn radius" type="button" onclick="checkId()">중복확인</button>
        </div>

        <div class="form-group">
            <i class="fa-solid fa-lock"></i>
            <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
        </div>

        <div class="form-group">
            <i class="fa-solid fa-id-card"></i>
            <input type="text" class="form-control" id="ownerName" name="ownerName" placeholder="이름">
        </div>

        <div class="form-group">
            <i class="fa-solid fa-building-columns"></i>
            <select class="form-control" id="bank" name="bank" required>
                <option disabled hidden selected>--- 은행을 선택해 주세요 ---</option>
                <option value="신한은행">신한은행</option>
                <option value="국민은행">국민은행</option>
                <option value="농협은행">농협은행</option>
                <option value="IBK기업은행">기업은행</option>
                <option value="BNK부산은행">부산은행</option>
                <option value="토스뱅크">토스뱅크</option>
                <option value="카카오뱅크">카카오뱅크</option>
                <option value="케이뱅크">케이뱅크</option>
            </select>
        </div>

        <div class="form-group">
            <i class="fa-solid fa-credit-card"></i>
            <input type="text" class="form-control" id="accountNumber" name="accountNumber" oninput="numberCheck()"
                   placeholder="계좌번호">
        </div>

        <div class="form-group btn">
            <button type="button" class="submit-btn" onclick="signUp()">가입하기</button>
        </div>
    </form>


</div>

<jsp:include page="component/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/js/owners/signUp.js"></script>
</html>