const getRoomList = () => {
  const hotelId = document.getElementById("hidden").value;
  axios.get('/v1/owners/rooms', {
    hotelId
  });
}