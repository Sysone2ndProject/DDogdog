const setStartDate = () => {

  let sDate = new Date();
  let eDate = new Date();

  sDate.setDate(sDate.getDate());
  eDate.setDate(eDate.getDate() + 365);

  const sYear = sDate.getFullYear();
  const sMonth = sDate.getMonth() + 1;
  const sDay = sDate.getDate();
  const eYear = eDate.getFullYear();
  const eMonth = eDate.getMonth() + 1;
  const eDay = eDate.getDate();

  let minDate = `${sYear}-${sMonth >= 10 ? sMonth : '0' + sMonth}-${sDay >= 10
      ? sDay : '0' + sDay}`;
  let maxDate = `${eYear}-${eMonth >= 10 ? eMonth : '0' + eMonth}-${eDay >= 10
      ? eDay : '0' + eDay}`;

  document.getElementById("startDate").min = minDate;
  document.getElementById("startDate").max = maxDate;
  document.getElementById("endDate").min = minDate;
  document.getElementById("endDate").max = maxDate;

}

const setEndDate = () => {
  let date = document.getElementById("startDate").value;

  let sYear = parseInt(date.split('-')[0]);
  let sMonth = date.split('-')[1];
  let sDay = parseInt(date.split('-')[2]) + 1;
  let minDate = `${sYear}-${sMonth}-${sDay}`;
  let eYear = parseInt(date.split('-')[0]) + 1;
  let eMonth = date.split('-')[1];
  let eDay = parseInt(date.split('-')[2]) + 1;
  let maxDate = `${eYear}-${eMonth}-${eDay}`

  document.getElementById("endDate").min = minDate;
  document.getElementById("endDate").max = maxDate;
}

window.addEventListener('DOMContentLoaded', setStartDate);
