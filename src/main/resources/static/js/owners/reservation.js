document.addEventListener('DOMContentLoaded', function () {
  const calendarEl = document.getElementById("calendar");
  let currentHotelId = null; // 초기 hotelId는 null

  // FullCalendar 인스턴스 초기화
  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    expandRows: true,
    headerToolbar: {
      start: "",
      center: 'prev title next',
      end: ""
    },
    selectable: true, //날짜 선택 or 드래그 가능
    displayEventTime: false, // 시간 표시 제거
    locale: 'ko',
    fixedWeekCount: false,
    default: false,
    firstDay: 0, // 0이 일요일
    nowIndicator: true, // 현재 시간 표시
    events: function (info, successCallback, failureCallback) {

      const start = new Date(info.start).getTime();
      const end = new Date(info.end).getTime();
      const middle = new Date((start + end) / 2);
      const year = middle.getFullYear();
      const month = middle.getMonth() + 1;

      if (currentHotelId) {
        getReservation(currentHotelId, year, month, successCallback,
            failureCallback);
      } else {
        successCallback([]); // hotelId가 없으면 빈 캘린더 표시
      }
    },
    dateClick: function (info) {
      const year = info.date.getFullYear();
      const month = info.date.getMonth() + 1;
      const date = info.date.getDate();
      console.log(year, month, date)
      popOver(currentHotelId, year, month, date);  // 팝오버를 여는 함수
    }
  });

  calendar.render(); // 여기로 이동해야 정상 동작

  // 호텔 버튼 클릭 이벤트 설정
  const hotelButtons = document.querySelectorAll('.hotel button');

  hotelButtons.forEach(button => {
    button.addEventListener('click', function () {
      const hotelId = this.querySelector('input[type="hidden"]').value;

      if (hotelId !== currentHotelId) {
        currentHotelId = hotelId;  // 클릭된 호텔의 ID 설정
        calendar.refetchEvents();  // 캘린더의 이벤트 새로고침 (예약 정보 갱신)
      }
    });
  });
});

const getReservation = (hotelId, year, month, successCallback,
    failureCallback) => {
  axios.get('/v1/owners/reservations', {
    params: {
      hotelId: hotelId,
      year: year,
      month: month
    }
  })
  .then(response => {
    let totalPrice = 0;

    const events = response.data.map(reservation => {
      totalPrice += reservation.price; // 가격 누적

      let className = '';
      let backgroundColor = '';

      switch (reservation.grade) {
        case 'DELUXE':
          className = 'event-deluxe';
          backgroundColor = 'lightblue'; // DELUXE 색상
          break;
        case 'SUPERIOR':
          className = 'event-superior';
          backgroundColor = 'lightgreen'; // SUPERIOR 색상
          break;
        case 'SUITE':
          className = 'event-suite';
          backgroundColor = 'lightcoral'; // SUITE 색상
          break;
        case 'ROYAL_SUITE':
          className = 'event-royal-suite';
          backgroundColor = 'gold'; // ROYAL_SUITE 색상
          break;
        default:
          className = 'event-default';
          backgroundColor = 'gray'; // 기본 색상
      }

      return {
        title: `${reservation.grade} - ${reservation.roomCount} rooms`,
        start: reservation.reservationDate,
        extendedProps: {
          price: reservation.price
        },
        classNames: [className], // CSS 클래스 추가
        backgroundColor: backgroundColor // 배경색 설정
      };
    });

    // 원화 형식 변환
    const formattedPrice = new Intl.NumberFormat('ko-KR').format(totalPrice);

    const income = document.getElementById("income");
    income.innerHTML = `총 수입: <span>${formattedPrice}</span>원`;

    successCallback(events); // 캘린더에 이벤트 등록

  })
  .catch(error => {
    console.error(error);
    failureCallback(error); // 실패 시 오류 처리
  });
}

const popOver = (hotelId, year, month, date) => {
  axios.get('/v1/owners/reservations/day', {
    params: {
      hotelId: hotelId,
      year: year,
      month: month,
      date: date
    }
  })
  .then(response => {
    document.getElementById(
        'reservationModalLabel').innerHTML = `${year}년 ${month}월 ${date}일`;
    const reservations = response.data.map(reservation => {
      return `<li>${reservation.grade} - ${reservation.customerName}님 - ${reservation.roomCount} rooms</li>`;
    }).join('');

    // 모달의 리스트 업데이트
    document.getElementById('reservationList').innerHTML = reservations;

    // 모달 표시
    $('#reservationModal').modal('show'); // jQuery를 사용하여 모달 표시
  })
  .catch(error => {
    console.error(error);
  });
}
