document.querySelectorAll('.pet-item').forEach(item => {
    // 클릭할 때마다 'data-id' 속성에서 값을 읽어옴
    item.addEventListener('click', function () {
        const petId = this.getAttribute('data-id');  // 클릭 시마다 읽음
        console.log("Clicked petId: " + petId);  // petId 값이 제대로 나오는지 확인
        window.location.href = `/v1/customers/pets/` + petId; // 올바른 경로로 이동
    });
});
