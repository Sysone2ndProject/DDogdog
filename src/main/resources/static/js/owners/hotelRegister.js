document.getElementById('hotelRegisterForm').onsubmit =
    function (event) {
      event.preventDefault(); //제출 중 새로고침 방지

      const formData = new FormData();
      formData.append('businessName',
          document.getElementById("business-name").value);
      formData.append('businessNumber',
          document.getElementById("business-number").value);
      formData.append('phoneNumber',
          document.getElementById("phone-number").value);
      formData.append('mainImage',
          document.getElementById("main-image").files[0]);
      formData.append('intro',
          document.getElementById("intro").value);
      formData.append('fullAddress',
          document.getElementById("address").value);
      formData.append('sido',
          document.getElementById("sido").value);
      formData.append('sigungu',
          document.getElementById("sigungu").value);
      formData.append('dong',
          document.getElementById("dong").value);

      axios.post('/v1/owners/hotels', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        alert('저장되었습니다.');
      })
      .catch(function (error) {
        alert('오류가 발생했습니다.');
      });

    }
const findAddress = () => {
  new daum.Postcode({
    oncomplete: function (data) {
      let fullAddress = ''; // 도로명주소 고정

      if (data.roadAddress !== "") {
        fullAddress = data.roadAddress;
      } else {
        fullAddress = data.autoRoadAddress;
      }
      //주소 input필드에 입력
      document.getElementById("address").value = fullAddress;
      document.getElementById("sido").value = data.sido;
      document.getElementById("sigungu").value = data.sigungu;
      document.getElementById("dong").value = data.bname;
    }
  }).open();
}

const previewThumnail = (event) => {
  const file = event.target.files[0]; // 선택한 파일 가져오기
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      const preview = document.getElementById('thumbnail-preview');
      preview.src = e.target.result; // 이미지 미리보기 src 설정
      preview.style.display = 'block'; //이미지 미리보기 div 화면 보이기
    };
    reader.readAsDataURL(file); // 파일을 Data URL로 변환
  }

}