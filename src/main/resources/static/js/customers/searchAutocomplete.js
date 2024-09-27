function searchAutoComplete() {
  const keyword = document.getElementById('keyword').value.trim();
  const autoSearchArea = document.getElementById('autoSearchArea');

  if (keyword === "") {
    // 입력 필드가 비어 있으면 자동완성 목록을 숨김
    autoSearchArea.style.display = 'none';
    return;
  }

  // 서버에 요청을 보내 검색어에 맞는 결과를 가져옴
  axios.get('/v1/customers/getDataList', {
    params: { searchKeyword: keyword }
  })
  .then(function(response) {
    const data = response.data;

    // 결과가 있으면 자동완성 목록을 표시
    if (data.length > 0) {
      autoSearchArea.innerHTML = '';  // 이전 결과 초기화

      // 검색 결과를 HTML로 렌더링
      data.forEach(item => {
        const label = document.createElement('label');
        label.classList.add('autoSearchingLabel');
        label.textContent = item;  // 검색된 이름 표시
        label.setAttribute('data-value', item);

        // 클릭 시 입력 필드에 선택한 값을 넣음
        label.addEventListener('click', function() {
          document.getElementById('keyword').value = item;
          autoSearchArea.style.display = 'none';  // 선택 후 자동완성 목록 숨김
        });

        autoSearchArea.appendChild(label);
      });

      autoSearchArea.style.display = 'block';  // 자동완성 목록을 표시
    } else {
      // 결과가 없으면 목록 숨김
      autoSearchArea.style.display = 'none';
    }
  })
  .catch(function(error) {
    console.error('Error fetching data:', error);
  });
}

document.addEventListener("DOMContentLoaded", searchAutoComplete)
