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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="header-box">
    <jsp:include page="component/header.jsp"></jsp:include>
</div>
<!-- 테스트용 입니다. -->
<div class="container">
    <h1>회원가입</h1>
    <form id="signUpForm" action="${pageContext.request.contextPath}/v1/owners/signup" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label for="id">아이디:</label>
            <input type="text" class="form-control" id="id" name="id">
            <button type="button" onclick="checkId()">중복확인</button>
        </div>
        <div class="form-group">
            <label for="password">password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="form-group">
            <label for="ownerName">이름:</label>
            <input type="text" class="form-control" id="ownerName" name="ownerName">
        </div>
        <div class="form-group">
            <label for="bank">은행 (Bank):</label>
            <select id="bank" name="bank" required>
                <option disabled hidden selected>
                    --- 은행을 선택해 주세요 ---
                </option>
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
            <label for="accountNumber">계좌번호</label>
            <input type="text" class="form-control" id="accountNumber" name="accountNumber" oninput="numberCheck()"
                   placeholder="숫자만 입력해 주세요">
        </div>
        <button type="button" class="btn btn-success" onclick="signUp()">가입하기</button>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/owners/signup.js"></script>
</html>