document.getElementById('updateRoomForm').onsubmit =
    function (event) {
      event.preventDefault(); //제출 중 새로고침 방지

      const priceValue = document.getElementById("price").value;
      const priceNum = parseInt(priceValue.replace(/,/g,''),10)

      const file = document.getElementById("imageUpload").files[0];
      const hotelId = document.getElementById("hotelId").value;

      const formData = new FormData();
      formData.append('hotelId', hotelId);
      formData.append('grade',
          document.getElementById("title").innerText);
      formData.append('roomCount',
          document.getElementById("roomCount").value);
      formData.append('maxDogs',
          document.getElementById("maxDogs").value);
      formData.append('price',
          priceNum);
      formData.append('intro',
          document.getElementById("intro").value);
      if (file) {
        //파일이 존재할 경우에만 추가
        formData.append('roomImage',
            file);
      }

      axios.put('/v1/owners/rooms', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        alert('저장되었습니다.');
        window.location.href = `/v1/owners/rooms?hotelId=${hotelId}`;
      })
      .catch(function (error) {
        alert('오류가 발생했습니다.');
      });

    }

const openFileUpload = () => {
  document.getElementById('image-upload').click();
}

const previewImage = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();

    reader.onload = function (e) {

      document.getElementById('roomImg').src = e.target.result;
    }

    reader.readAsDataURL(file);
  }
}

const moneyFormatter = (event) =>{
  const value = event.target.value.replace(/[^0-9]/g, '');  // 숫자 이외의 문자 제거
  event.target.value = Number(value).toLocaleString();  // 3자리마다 쉼표 추가
}

const maxNum = (event) =>{
  const maxRooms = 30;
  const value = event.target.value;

  if (value > maxRooms) {
    alert('최대 방수는 30개입니다.');
    event.target.value = maxRooms;  // 입력값을 최대값인 30으로 고정
  }
}