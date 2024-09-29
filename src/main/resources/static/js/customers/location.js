let address = "";
let sido = "";
let sigungu = "";

function find_address() {
  new daum.Postcode({
    oncomplete: function(data) {
      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

      if (data.userSelectedType === 'R') { // 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else { // 지번 주소를 선택했을 경우
        addr = data.jibunAddress;
      }

      if(data.userSelectedType === 'R'){
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraAddr += data.bname;
        }
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        document.getElementById("extraAddress").value = extraAddr;
      } else {
        document.getElementById("extraAddress").value = '';
      }

      document.getElementById('postcode').value = data.zonecode;
      document.getElementById("address").value = addr;
      address = data.address;
      sido = data.sido;
      sigungu = data.sigungu;
      bname = data.bname;
    }
  }).open();
}

document.getElementById('addressForm').addEventListener('submit', function(event) {
  event.preventDefault(); // 폼 제출을 막습니다.
  const customData = {
    fullAddress: address,
    sido,
    sigungu,
    bname,
    kakaoId: id
  };
  console.log(customData);
  axios.post('/v1/customers/signup', customData)
  .then(response => {
    console.log('Data submitted successfully:', response.data);
    window.location.href = '/v1/customers';
   })
  .catch(error => {
    console.error('Error submitting data:', error);
    // 오류 시 처리 로직
  });
});