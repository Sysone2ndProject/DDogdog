<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title>추가정보 등록</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/customers/location.css">
</head>
<body>
<div class="container">

    <h1> 회원가입을 완료하시려면 주소를 입력해주세요!</h1>
    <form id="addressForm">
        <input type="text" id="postcode" placeholder="우편번호">
        <input type="text" id="address" placeholder="주소">
        <input type="text" id="extraAddress" placeholder="참고항목">
        <div class="buttons">
            <input type="submit" value="회원가입">
            <input type="button" onclick="find_address()" value="주소 찾기"><br>
        </div>
    </form>
</div>
<jsp:include page="../component/footer.jsp"></jsp:include>
</body>
<script>
  let id = ${id};
</script>
<!-- JavaScript 파일 로드 -->
<script src="/js/customers/location.js"></script>
</html>
