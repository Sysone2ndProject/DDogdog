document.getElementById('searchForm').onsubmit =
    function (event) {
      event.preventDefault();

      const keyword = document.getElementById('keyword').value;
      const startDate = document.getElementById('startDate').value;
      const endDate = document.getElementById('endDate').value;

      axios.get('/v1/customers/hotels', {
        params: {
          keyword: keyword,
          startDate: startDate,
          endDate: endDate
        }
      })
      .then(function (response) {
        console.log(response.data);
        window.location.href = '/v1/customers/hotels?keyword='
            + encodeURIComponent(keyword) + '&startDate=' + encodeURIComponent(
                startDate) + '&endDate=' + encodeURIComponent(endDate);
      })
      .catch(function (error) {
        console.error('Error:', error);
      });
    };

const gotoDetail = (id) => {
  axios.get(`/v1/customers/hotels/${id}`)
  .then(response => {
    window.location.href = `/v1/customers/hotels/${id}`;
  }).catch(error => {
    console.error();
  })
}
