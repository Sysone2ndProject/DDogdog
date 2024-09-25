// 포맷팅 함수 (연도-월-일 형식으로 포맷)
const formatDate = (date) => {
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
};

const setStartDate = () => {
  let sDate = new Date(); // 오늘 날짜
  let eDate = new Date(); // 1년 후 날짜

  eDate.setFullYear(eDate.getFullYear() + 1);

  const minDate = formatDate(sDate);
  const maxDate = formatDate(eDate);

  document.getElementById("startDate").min = minDate;
  document.getElementById("startDate").max = maxDate;
  document.getElementById("endDate").min = minDate;
  document.getElementById("endDate").max = maxDate;
};

const setEndDate = () => {
  let startDateValue = document.getElementById("startDate").value;

  let startDate = new Date(startDateValue);

  let minEndDate = new Date(startDate);
  minEndDate.setDate(minEndDate.getDate() + 1);

  let maxEndDate = new Date(startDate);
  maxEndDate.setDate(minEndDate.getDate() + 1);
  maxEndDate.setFullYear(maxEndDate.getFullYear() + 1);

  // 종료 날짜의 최소값과 최대값 설정
  document.getElementById("endDate").min = formatDate(minEndDate);
  document.getElementById("endDate").max = formatDate(maxEndDate);

  // 체크 아웃 날짜 선택 초기화
  document.getElementById("endDate").value = "";
};

const checkStartDate = () => {
  let startDateValue = document.getElementById("startDate").value;

  if (!startDateValue) {
    alert("먼저 체크인 날짜를 선택해 주세요.");
    return null;
  }
}

// 페이지가 로드되면 setStartDate 실행
window.addEventListener('DOMContentLoaded', setStartDate);
