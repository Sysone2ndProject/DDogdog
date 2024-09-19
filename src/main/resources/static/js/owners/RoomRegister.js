document.getElementById('roomRegisterForm').onsubmit =
    function (event) {
      event.preventDefault();

      const path = window.location.pathname;
      const pathParts = path.split('/');
      const hotelId = pathParts[0];

      const formData = new FormData();
      formData.append('hotelId', hotelId);
      formData.append('grade', document.getElementById('room-grade').value);
      formData.append('roomCount', document.getElementById('room-count').value);
      formData.append('price', document.getElementById('room-price').value);
      formData.append('maxDogs', document.getElementById('max-dogs').value);
      formData.append('roomImage',
          document.getElementById('room-photo').files[0])
      formData.append('intro', document.getElementById('intro').value);

      axios.post('/v1/owners/rooms', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        alert('객실 등록 되었습니다.');
        //현재 호텔리스트 페이지 미완성으로 메인으로 이동
        //TODO:유저 호텔 리스트 페이지 api 생성 후 해당 페이지로 이동
        window.location.href = '/v1/owners';
      })
      .catch(function (error) {
        alert('오류가 발생했습니다.');
      });
    }

const previewRoomImg = (event) => {
  const path = window.location.pathname;
  const pathParts = path.split('/');
  const hotelId = pathParts[-1];
  console.log(hotelId);
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      const preview = document.getElementById('room-img-review');
      preview.src = e.target.result;
      preview.style.display = 'block';
    };
    reader.readAsDataURL(file);
  }
}