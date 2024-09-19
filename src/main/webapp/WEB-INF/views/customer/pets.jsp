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
    <style>
      .pet-item {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s, box-shadow 0.3s;
      }
      .pet-item:hover {
        background-color: #f9f9f9;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      }
      .pet-item img {
        margin-right: 10px;
        border-radius: 50%;
      }
      .pet-info {
        display: flex;
        flex-direction: column;
      }
      .add-button {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100px;
        height: 100px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #f0f0f0;
        cursor: pointer;
        transition: background-color 0.3s, box-shadow 0.3s;
        text-align: center;
        line-height: 100px;
        font-size: 24px;
        color: #333;
      }
      .add-button:hover {
        background-color: #e0e0e0;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      }
    </style>
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<div class="container">
    <h2>펫 목록</h2>
    <ul>
        <c:forEach var="pet" items="${pets}">
            <li class="pet-item" data-id="${pet.id}">
                <img src="${pet.petImage}" alt="${pet.name}" style="width:100px; height:auto;">
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

<script>
  document.querySelectorAll('.pet-item').forEach(item => {
    // 클릭할 때마다 'data-id' 속성에서 값을 읽어옴
    item.addEventListener('click', function() {
      const petId = this.getAttribute('data-id');  // 클릭 시마다 읽음
      console.log("Clicked petId: " + petId);  // petId 값이 제대로 나오는지 확인
      window.location.href = `/v1/customers/pets/`+petId; // 올바른 경로로 이동
    });
  });
</script>
</body>
</html>
