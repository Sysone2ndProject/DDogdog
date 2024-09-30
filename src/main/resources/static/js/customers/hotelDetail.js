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
    Swal.fire({
      title: '날짜를 선택하세요.',
      icon: 'error',
      confirmButtonText: '확인'
    })
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
  document.getElementById("totalPrice").innerText = totalPrice.toLocaleString(
      'ko-KR');
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

  if (totalCnt === 0) {
    Swal.fire({
      title: '방을 선택하세요.',
      icon: 'error',
      confirmButtonText: '확인'
    })
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
    Swal.fire({
      title: '예약이 완료되었습니다.',
      icon: 'success',
      confirmButtonText: '확인'
    }).then(() => {
      window.location.href = "/v1/customers/member"
    });
  })
  .catch((error) => {
    console.error(error);
    Swal.fire({
      title: '로그인 후 이용해주세요.',
      icon: 'error',
      confirmButtonText: '확인'
    })
  });
};

const goToForm = () => {
  window.scrollTo(
      {top: document.getElementById("room").offsetTop, behavior: 'smooth'});
}
document.getElementById('reservationForm').onsubmit = (event) => submitForm(
    event);

const formatPhoneNumber = () => {
  let phoneNumber = document.getElementById("phone").innerText;

  if (phoneNumber.length === 9) {
    document.getElementById("phone").textContent = phoneNumber.replace(
        /(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
  } else if (phoneNumber.startsWith('02') && phoneNumber.length === 10) {
    document.getElementById("phone").textContent = phoneNumber.replace(
        /(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
  } else if (phoneNumber.length === 10) {
    document.getElementById("phone").textContent = phoneNumber.replace(
        /(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
  } else {
    document.getElementById("phone").textContent = phoneNumber.replace(
        /(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
  }
}
