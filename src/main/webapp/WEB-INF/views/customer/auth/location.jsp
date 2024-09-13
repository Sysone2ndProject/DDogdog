<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>추가정보 등록</title>
</head>
<body>
<h1> 회원가입을 완료하시려면 주소를 입력해주세요!</h1>
<form id="addressForm">
    <input type="text" id="postcode" placeholder="우편번호">
    <input type="text" id="address" placeholder="주소"><br>
    <input type="text" id="extraAddress" placeholder="참고항목">
    <input type="submit" value="회원가입">
</form>
<input type="button" onclick="find_address()" value="주소 찾기"><br>

<script>
  let id = ${id};
</script>
<!-- JavaScript 파일 로드 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/customers/location.js"></script>
</body>
</html>
