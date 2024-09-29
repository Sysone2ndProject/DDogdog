document.getElementById('hotelRegisterForm').onsubmit =
    function (event) {
      event.preventDefault(); //제출 중 새로고침 방지

      const phoneNumber = document.getElementById("phoneNumber").value;

      // 전화번호 유효성 검사 (숫자 9자리에서 11자리)
      if (!/^\d{9,11}$/.test(phoneNumber)) {
        Swal.fire({
          title: '숫자만 입력해 주세요',
          text: '전화번호는 숫자 9자리에서 11자리이하로 입력해주세요',
          icon: 'warning',
        })
        event.preventDefault(); // 폼 제출을 막음
      }

      const formData = new FormData();
      formData.append('businessName',
          document.getElementById("businessName").value);
      formData.append('businessNumber',
          document.getElementById("businessNumber").value);
      formData.append('phoneNumber',
          phoneNumber);
      formData.append('mainImage',
          document.getElementById("mainImage").files[0]);
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
        Swal.fire({
          title: '저장되었습니다.',
          icon: 'success',
        }).then((result) => {
          if (result.isConfirmed) {
            window.location.href = `/v1/owners/hotels`;
          }
        });
      })
      .catch(function (error) {
        Swal.fire({
          title: '오류가 발생했습니다..',
          icon: 'error',
        })
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
      const preview = document.getElementById('thumbnailPreview');
      preview.src = e.target.result; // 이미지 미리보기 src 설정
      preview.style.display = 'block'; //이미지 미리보기 div 화면 보이기
    };
    reader.readAsDataURL(file); // 파일을 Data URL로 변환
  }

}

function businessNumberCheck() {
  const businessNumber = document.getElementById('businessNumber');
  const numericPattern = /^[0-9]*$/;
  if (!numericPattern.test(businessNumber.value)) {
    Swal.fire({
      title: '사업자 번호는 숫자만 입력해주세요',
      icon: 'warning',
    })
    businessNumber.value = businessNumber.value.replace(/[^0-9]/g, '');
  }
}

function phoneNumberCheck() {
  const phoneNumber = document.getElementById('phoneNumber');
  const numericPattern = /^[0-9]*$/;
  if (!numericPattern.test(phoneNumber.value)) {
    Swal.fire({
      title: '숫자만 입력해 주세요',
      text: '전화번호는 숫자 9자리에서 11자리이하로 입력해주세요',
      icon: 'warning',
    })
    phoneNumber.value = phoneNumber.value.replace(/[^0-9]/g, '');
  }
}