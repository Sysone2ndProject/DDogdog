document.getElementById('roomRegisterForm').onsubmit =
    function (event) {
      event.preventDefault();

      const hotelId = document.getElementById('hotelIdHidden').value;
      const formData = new FormData();
      formData.append('hotelId',
          document.getElementById('hotelIdHidden').value);
      formData.append('grade', document.getElementById('roomGrade').value);
      formData.append('roomCount', document.getElementById('roomCount').value);
      formData.append('price', document.getElementById('roomPrice').value);
      formData.append('maxDogs', document.getElementById('maxDogs').value);
      formData.append('roomImage',
          document.getElementById('roomPhoto').files[0])
      formData.append('intro', document.getElementById('intro').value);

      axios.post('/v1/owners/rooms', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(function (response) {
        alert('객실 등록 되었습니다.');
        window.location.href = `/v1/owners/rooms?hotelId=${hotelId}`;
      })
      .catch(function (error) {
        alert('오류가 발생했습니다.');
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