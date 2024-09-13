document.getElementById('searchForm').addEventListener('submit',
    function (event) {
      event.preventDefault();

      const keyword = document.getElementById('keyword').value;
      // const startDate = document.getElementById('start').value;
      // const endDate = document.getElementById('end').value;

      axios.get('/v1/customers/hotels', {
        params: {
          keyword: keyword,
          // startDate: startDate,
          // endDate: endDate
        }
      })
      .then(function (response) {
        console.log(response.data);
        window.location.href = '/v1/customers/hotels?keyword='
            + encodeURIComponent(keyword);
      })
      .catch(function (error) {
        console.error('Error:', error);
      });
    });
