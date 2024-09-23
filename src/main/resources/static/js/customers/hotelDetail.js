let startDate = "";
let endDate = "";
let totalCnt = parseInt(document.getElementById("totalCount").innerText);
let totalPrice = parseInt(document.getElementById("totalPrice").innerText);

const getDayDiffDay = (startDate, endDate) =>
    (endDate - startDate) / (1000 * 3600 * 24);

const getStartDate = () => {
  startDate = document.getElementById("startDate").value;
}

const getEndDate = () => {
  endDate = document.getElementById("endDate").value;
}

const calcCountAndPrice = (i) => {

  if (startDate === "" || endDate === "") {
    alert("날짜를 선택해주세요");
    document.getElementById(`count${i}`).value = 0;
    return;
  }

  let beforeCnt = rooms[i].count;
  let afterCnt = parseInt(document.getElementById(`count${i}`).value);

  let days = getDayDiffDay(new Date(startDate), new Date(endDate));

  totalPrice += (afterCnt - beforeCnt) * rooms[i].price * days;
  totalCnt += (afterCnt - beforeCnt);
  rooms[i].count = afterCnt;
  document.getElementById("totalCount").innerText = totalCnt;
  document.getElementById("totalPrice").innerText = totalPrice;
}

const submitForm = (event) => {
  event.preventDefault();

  if (startDate === "" || endDate === "") {
    alert("날짜를 선택해주세요");
    return;
  } else if (totalCnt === 0) {
    alert("방을 선택해주세요");
    return;
  }

  axios.post('업데이트 url', {
    body: {
      hotelId,
      startDate,
      endDate,
      count: totalCnt,
      price: totalPrice,
      rooms
    }
  })
  .then((response) => {
    console.log(response.data)
  })
  .catch((error) => {
    console.error(error)
  });
};

document.getElementById('reservationForm').onsubmit = (event) => submitForm(event);
