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
  document.getElementById("totalPrice").innerText = totalPrice.toLocaleString('ko-KR');
}

// 숫자 값을 증가시키는 함수
const increaseValue = (index) => {
  const input = document.getElementById(`count${index}`);
  let value = parseInt(input.value);

  if (!isNaN(value)) {
    value++;
    input.value = value;
    calcCountAndPrice(index);
  }
}

// 숫자 값을 감소시키는 함수
const decreaseValue = (index) => {
  const input = document.getElementById(`count${index}`);
  let value = parseInt(input.value);

  if (!isNaN(value) && value > 0) {
    value--;
    input.value = value;
    calcCountAndPrice(index);
  }
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
  axios.post('/v1/customers/reservation', {
      hotelId,
      startDate,
      endDate,
      count: totalCnt,
      price: totalPrice,
      rooms
  })
  .then((response) => {
    console.log(response.data);
    alert("예약이 완료되었습니다.");
    window.location.href="/v1/customers/member";
  })
  .catch((error) => {
    console.error(error);
  });
};

const goToForm = () => {
  console.log(1);
  window.scrollTo({top: document.getElementById("room").offsetTop, behavior: 'smooth' });
}
document.getElementById('reservationForm').onsubmit = (event) => submitForm(event);

document.addEventListener('DOMContentLoaded', () => {

})
