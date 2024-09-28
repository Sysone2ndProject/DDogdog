const deleteKeyword = () => {
  document.querySelector('.text-input').value = "";
}

document.getElementById('searchForm').onsubmit =
    (event) => {
      event.preventDefault();

      const keyword = document.getElementById('keyword').value || '';
      const startDate = document.getElementById('startDate').value || '';
      const endDate = document.getElementById('endDate').value || '';

      if (keyword === "" || startDate === "" || endDate === "") {
        alert("모든 정보를 입력해주세요");
        return;
      }

      axios.get('/v1/customers/hotels', {
        params: {
          keyword: keyword,
          startDate: startDate,
          endDate: endDate
        }
      })
      .then((response) => {
        console.log(response.data);
        window.location.href = '/v1/customers/hotels?keyword='
            + encodeURIComponent(keyword) + '&startDate=' + encodeURIComponent(
                startDate) + '&endDate=' + encodeURIComponent(endDate);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
    };

const gotoDetail = (id) => {
  axios.get(`/v1/customers/hotels/${id}`)
  .then(response => {
    window.location.href = `/v1/customers/hotels/${id}`;
  }).catch(error => {
    console.error();
  });
}

formatPhoneNumber = () => {
  let phoneNumber = document.getElementById("phone").innerText;
  document.getElementById("phone").textContent = phoneNumber.replace(
      /(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
}

document.addEventListener("DOMContentLoaded", formatPhoneNumber);