const submitForm = (id,startDate, endDate, rooms ) => {
  axios.post('업데이트 url',{
    body: {
      hotelId:id,
      startDate,
      endDate,
      count:,
      price:,
      rooms

    }
  })
}

// id, startDate, endDate, count, price, rooms 정의

document.getElementById('reservationForm').onsubmit = submitForm();

