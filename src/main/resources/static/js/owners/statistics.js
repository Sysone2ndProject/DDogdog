let yearIncomeChart;
let gradeIncomeChart;
let gradeRoomCountChart;
let currentHotelId;

document.addEventListener("DOMContentLoaded", function () {

  const yearStatics = document.getElementById('monthIncome').getContext('2d');
  const gradeStatics = document.getElementById('gradeIncome').getContext('2d');
  const roomCountStatics = document.getElementById('gradeRoomCount').getContext(
      '2d');

  // 총 가격 차트 설정
  yearIncomeChart = new Chart(yearStatics, {
    type: 'bar',
    data: {
      labels: [],
      datasets: [{
        label: '월별 총 매출',
        data: [],
        backgroundColor: 'rgb(255, 107, 107)',
        borderColor: 'rgb(255, 107, 107)',
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,  // 반응형 설정
      maintainAspectRatio: true, // 비율 유지
      plugins: {
        legend: {position: 'top'},
        title: {display: true, text: '연간 월 매출 통계'}
      },
      scales: {
        x: {beginAtZero: true},
        y: {beginAtZero: true, position: 'left'}
      }
    }
  });

  // 등급별 매출 차트 설정
  gradeIncomeChart = new Chart(gradeStatics, {
    type: 'doughnut',
    data: {
      labels: ['DELUXE', 'SUPERIOR', 'SUITE', 'ROYAL_SUITE'],
      datasets: [{
        label: '매출',
        data: [0, 0, 0, 0],
        backgroundColor: [
          'rgb(255, 213, 79)', // DELUXE 색상
          'rgb(79, 195, 247)', // SUPERIOR 색상
          'rgb(149, 117, 205)', // SUITE 색상
          'rgb(277, 182, 172)'  // ROYAL_SUITE 색상
        ],
        borderColor: [
          'rgba(255, 213, 79, 1)',
          'rgba(79, 195, 247, 1)',
          'rgba(149, 117, 205, 1)',
          'rgba(277, 182, 172, 1)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,  // 반응형 설정
      maintainAspectRatio: true, // 비율 유지
      plugins: {
        title: {display: true, text: '등급별 연간 매출'},
        datalabels: {
          display: true,
          formatter: (value, ctx) => {
            let label = ctx.chart.data.labels[ctx.dataIndex];
            return label + '\n' + value + '원';
          }
        }
      }
    }
  });

  // 등급별 객실 수 차트 설정
  gradeRoomCountChart = new Chart(roomCountStatics, {
    type: 'doughnut',
    data: {
      labels: ['DELUXE', 'SUPERIOR', 'SUITE', 'ROYAL_SUITE'],
      datasets: [{
        label: '객실 수',
        data: [0, 0, 0, 0],
        backgroundColor: [
          'rgb(255, 213, 79)', // DELUXE 색상
          'rgb(79, 195, 247)', // SUPERIOR 색상
          'rgb(149, 117, 205)', // SUITE 색상
          'rgb(277, 182, 172)'  // ROYAL_SUITE 색상

        ],
        borderColor: [
          'rgba(255, 213, 79, 1)',
          'rgba(79, 195, 247, 1)',
          'rgba(149, 117, 205, 1)',
          'rgba(277, 182, 172, 1)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,  // 반응형 설정
      maintainAspectRatio: true, // 비율 유지
      plugins: {
        title: {display: true, text: '연간 객실 수 통계'},
        datalabels: {
          display: true,
          formatter: (value, ctx) => {
            let label = ctx.chart.data.labels[ctx.dataIndex];
            return label + '\n' + value + '개';
          }
        }
      }
    }
  });

  // 연도 선택 변경 시 이벤트 리스너
  document.getElementById("year").addEventListener("change", function () {
    reservationData(currentHotelId, this.value);
  });

  // 호텔 버튼 클릭 시 이벤트 리스너
  document.querySelectorAll('.hotel button').forEach(button => {
    button.addEventListener('click', function () {
      currentHotelId = this.querySelector('input[type="hidden"]').value;
      const year = document.getElementById("year").value;
      reservationData(currentHotelId, year);
    });
  });

  // 초기 데이터 로드
  const firstHotelId = document.querySelector(
      '.hotel button input[type="hidden"]').value;
  const currentYear = new Date().getFullYear();
  document.getElementById("year").value = currentYear; // 현재 연도 설정
  reservationData(firstHotelId, currentYear);
});

// 서버에서 예약 데이터를 가져오는 함수
const reservationData = (hotelId, year) => {
  axios.get('/v1/owners/reservations/year',
      {params: {hotelId: hotelId, year: year}})
  .then(response => updateCharts(response.data))
  .catch(error => console.error("데이터 가져오기 오류:", error));
}

// 차트 업데이트 함수
const updateCharts = (data) => {
  const months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10',
    '11', '12'];

  const monthData = data.reduce((acc, curr) => {
    const month = curr.month.split('-')[1];
    acc[month] = curr.totalPrice;
    return acc;
  }, {});

  const totalPrices = months.map(month => monthData[month] || 0);
  yearIncomeChart.data.labels = months.map(month => `${month}월`);
  yearIncomeChart.data.datasets[0].data = totalPrices;
  yearIncomeChart.update();

  const grades = ['DELUXE', 'SUPERIOR', 'SUITE', 'ROYAL_SUITE'];
  const gradeData = grades.reduce((acc, grade) => {
    acc[grade] = {price: 0, roomCount: 0};
    return acc;
  }, {});

  data.forEach(curr => {
    if (gradeData[curr.grade]) {
      gradeData[curr.grade].price += curr.price;
      gradeData[curr.grade].roomCount += curr.roomCount;
    }
  });

  gradeIncomeChart.data.datasets[0].data = grades.map(
      grade => gradeData[grade].price);
  gradeRoomCountChart.data.datasets[0].data = grades.map(
      grade => gradeData[grade].roomCount);

  gradeIncomeChart.update();
  gradeRoomCountChart.update();
};