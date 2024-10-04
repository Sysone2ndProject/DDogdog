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
  getStartDate();
  getEndDate();
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
      title: '예약 완료!',
      text: '예약이 완료되었습니다.',
      icon: 'success',
      confirmButtonText: '확인'
    }).then(() => {
      window.location.href = "/v1/customers/reservation";
    });
  })
  .catch((error) => {
    Swal.fire({
      title: '로그인 필요',
      text: '로그인 후 이용해주세요.',
      icon: 'error',
      confirmButtonText: '확인'
    });
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

const getRestRoom = (id) => {
  getStartDate();
  getEndDate();
  axios.get(`/v1/customers/hotels/more/${id}`, {
    params: {
      startDate,
      endDate
    }
  })
      .then(response => {
        // response.data에서 방 리스트 가져오기
        const rooms = response.data.rooms;

        // room-list를 초기화
        const roomListDiv = document.querySelector('.room-list');
        roomListDiv.innerHTML = '';  // 기존 내용 지우기

        // 방 정보를 HTML로 생성하고 삽입
        rooms.forEach((room, index) => {
          const roomItem = `
        <div class="room-item radius shadow">
          <img class="room-img" src="${room.roomImage}">
          <div class="room-info">
            <p class="room-grade">${room.grade}</p>
            <p class="subtext">${room.intro}</p>
            <p class="count">남은 방 개수: ${room.count}</p>
            <p class="small">1박</p>
            <div class="price-box">
              <span class="room-price">${room.price.toLocaleString('ko-KR')}₩</span>
              <div class="count-btn">
                <button class="minus" type="button" onclick="decreaseValue(${index})">-</button>
                <input id="count${index}" type="number" min="0" value="0">
                <button class="plus" type="button" onclick="increaseValue(${index})">+</button>
              </div>
            </div>
          </div>
        </div>`;

          // 생성된 HTML을 room-list에 추가
          roomListDiv.insertAdjacentHTML('beforeend', roomItem);
        });

      }).catch(error => {
    console.error();
  });
}