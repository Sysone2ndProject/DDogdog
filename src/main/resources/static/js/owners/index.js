document.addEventListener("DOMContentLoaded", () => {
  let today = new Date();

  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();

  let weekDays = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
  let currentDay = weekDays[today.getDay()];

  if (month < 10) month = '0' + month;
  if (day < 10) day = '0' + day;

  document.getElementById('nowDate').innerText = year + '년 ' + month + '월 ' + day + '일';
  document.getElementById('nowDay').innerText = currentDay;
})
