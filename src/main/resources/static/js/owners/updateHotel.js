document.getElementById('hotel-update-form').onsubmit =
    function (event) {
      event.preventDefault(); //제출 중 새로고침 방지

      const file = document.getElementById("image-upload").files[0];

      const formData = new FormData();
      formData.append('id', document.getElementById("hotel-id").value);
      formData.append('businessName',
          document.getElementById("business-name").value);
      formData.append('phoneNumber',
          document.getElementById("phone-number").value);
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
      if (file) {
        //파일이 존재할 경우에만 추가
        formData.append('mainImage',
            file);
      }

      axios.put('/v1/owners/hotels', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        alert('저장되었습니다.');
        window.location.href = `/v1/owners/hotels`;
      })
      .catch(function (error) {
        alert('오류가 발생했습니다.');
      });

    }

const openFileUpload = () => {
  document.getElementById('image-upload').click(); // 파일 업로드 창을 오픈
}

const previewImage = (event) => {
  const file = event.target.files[0]; // 선택된 파일을 가져옴
  if (file) {
    const reader = new FileReader(); // FileReader 객체 생성

    reader.onload = function (e) {
      // 파일이 로드되면 해당 이미지를 기존 이미지칸에서 보여주기
      document.getElementById('hotel-img').src = e.target.result;
    }

    reader.readAsDataURL(file); // 선택된 파일을 읽어서 data URL로 변환
  }
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