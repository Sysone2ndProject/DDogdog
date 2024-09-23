document.getElementById('update-room-form').onsubmit =
    function (event) {
      event.preventDefault(); //제출 중 새로고침 방지

      const file = document.getElementById("image-upload").files[0];
      const hotelId = document.getElementById("hotel-id").value;

      const formData = new FormData();
      formData.append('hotelId', hotelId);
      formData.append('grade',
          document.getElementById("title").innerText);
      formData.append('roomCount',
          document.getElementById("room-count").value);
      formData.append('maxDogs',
          document.getElementById("max-dogs").value);
      formData.append('price',
          document.getElementById("price").value);
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

      document.getElementById('room-img').src = e.target.result;
    }

    reader.readAsDataURL(file);
  }
}