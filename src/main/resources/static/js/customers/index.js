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
          keyword,
          startDate,
          endDate
        }
      })
      .then((response) => {
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

window.onload = () => {
  const urlParams = new URLSearchParams(window.location.search);
  const alertParam = urlParams.get('alert');
  if (alertParam === 'true') {
    Swal.fire({
      title: '로그인이 필요합니다!',
      text: '다음 버튼을 클릭하여 로그인해주세요.',
      icon: 'info',
      showCancelButton: true, // 취소 버튼 표시
      confirmButtonText: '로그인하기',
      cancelButtonText: '돌아가기', // 로그인하기 버튼
    }).then((result) => {
      if (result.isConfirmed) {
        // 확인 버튼 클릭 시
        window.location.href = '/oauth2/authorization/kakao';
      }
    });
  }
}
