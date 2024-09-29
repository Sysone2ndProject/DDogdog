document.getElementById('roomRegisterForm').onsubmit =
    function (event) {
      event.preventDefault();

      const hotelId = document.getElementById('hotelIdHidden').value;
      const priceValue = document.getElementById("roomPrice").value;
      const priceNum = parseInt(priceValue.replace(/,/g,''),10)

      const formData = new FormData();
      formData.append('hotelId',
          hotelId);
      formData.append('grade', document.getElementById('roomGrade').value);
      formData.append('roomCount', document.getElementById('roomCount').value);
      formData.append('price', priceNum);
      formData.append('maxDogs', document.getElementById('maxDogs').value);
      formData.append('roomImage',
          document.getElementById('roomImage').files[0])
      formData.append('intro', document.getElementById('intro').value);

      axios.post('/v1/owners/rooms', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        Swal.fire({
          title: '객실 등록 되었습니다.',
          icon: 'success',
        }).then((result) => {
          if (result.isConfirmed) {
            window.location.href = `/v1/owners/rooms?hotelId=${hotelId}`;
          }
        });
      })
      .catch(function (error) {
        Swal.fire({
          title: '오류가 발생했습니다.',
          icon: 'error',
        })
      });
    }

const previewRoomImg = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      const preview = document.getElementById('roomImgReview');
      preview.src = e.target.result;
      preview.style.display = 'block';
    };
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
    Swal.fire({
      title: '최대 방수는 30개입니다.',
      icon: 'warning',
    })
    event.target.value = maxRooms;  // 입력값을 최대값인 30으로 고정
  }
}
