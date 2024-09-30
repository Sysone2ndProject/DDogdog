let page = 1; // 현재 페이지
const size = 3; // 한 번에 불러올 항목 수
let isLoading = false; // 데이터를 불러오는 중인지 여부
let hasMoreData = true; // 더 불러올 데이터가 있는지 여부

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
        Swal.fire({
          title: '모든 정보를 입력해주세요.',
          icon: 'error',
          confirmButtonText: '확인'
        })
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

window.addEventListener('scroll', async function () {
  if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100) {
    if (!isLoading && hasMoreData) {
      isLoading = true; // 로딩 상태로 변경
      await loadMoreHotels(); // 데이터 불러오기
      isLoading = false; // 로딩 완료
    }
  }
});

// 호텔 데이터를 추가로 로드하는 함수
async function loadMoreHotels() {
  page++; // 페이지 증가
  const keyword = document.getElementById('keyword').value;
  const startDate = document.getElementById('startDate').value;
  const endDate = document.getElementById('endDate').value;

  try {
    const response = await axios.get('/v1/customers/hotels/more', {
      params: {
        keyword,
        startDate,
        endDate,
        page,
        size
      }
    });

    const hotels = response.data.content;
    hasMoreData = !response.data.last; // 마지막 페이지인지 여부 설정

    if (hotels.length > 0) {
      appendHotelsToList(hotels); // 호텔 리스트에 추가
    } else {
      console.log("불러올 호텔이 더 이상 없습니다.");
    }
  } catch (error) {
    console.error("호텔 데이터를 불러오는 중 오류가 발생했습니다.", error);
  }
}

const appendHotelsToList = (hotels) => {
  const hotelList = document.getElementById('hotelList');

  hotels.forEach(hotel => {
    const hotelElement = document.createElement('div');
    hotelElement.classList.add('hotel', 'radius', 'shadow');

    const formattedPrice = new Intl.NumberFormat('ko-KR').format(hotel.price);

    hotelElement.innerHTML = `
                    <img class="hotel-img" src="${hotel.mainImage}">
                    <div class="hotel-content">
                        <p class="hotel-name">${hotel.businessName}</p>
                        <div class="loc">
                            <span class="material-icons-outlined">location_on</span>
                            <span class="text-info">${hotel.fullAddress}</span>
                        </div>
                        <div class="cont">
                            <span class="material-icons-outlined">call</span>
                            <span class="phone">${hotel.phoneNumber}</span>
                        </div>
                        <p class="hotel-intro">${hotel.intro}</p>
                    </div>
                    <div class="price-btn">
                        <div class="price-title">
                            <span class="material-icons-outlined">calendar_today</span>
                            <span class="text-info">1 Day</span>
                        </div>
                        <p class="hotel-price">${formattedPrice} ₩</p>
                        <button class="button radius" onclick="gotoDetail(${hotel.id})">자세히 보기</button>
                    </div>
        `;
    hotelList.appendChild(hotelElement);
  });
  formatPhoneNumber();
}

const gotoDetail = (id) => {
  const startDate = document.getElementById('startDate').value;
  const endDate = document.getElementById('endDate').value;

  axios.get(`/v1/customers/hotels/${id}`, {
    params: {
      startDate,
      endDate
    }
  })
  .then(response => {
    window.location.href = `/v1/customers/hotels/${id}` + '?startDate='
        + encodeURIComponent(
            startDate) + '&endDate=' + encodeURIComponent(endDate);
  }).catch(error => {
    console.error();
  });
}

const formatPhoneNumber = () => {
  const phoneNumbers = document.querySelectorAll('.phone');

  phoneNumbers.forEach((phone) => {
    let phoneNumber = phone.innerText;

    if (phoneNumber.length === 9) {
      phone.textContent = phoneNumber.replace(
          /(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
    } else if (phoneNumber.startsWith('02') && phoneNumber.length === 10) {
      phone.textContent = phoneNumber.replace(
          /(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if (phoneNumber.length === 10) {
      phone.textContent = phoneNumber.replace(
          /(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    } else {
      phone.textContent = phoneNumber.replace(
          /(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    }
  })

}

document.addEventListener("DOMContentLoaded", formatPhoneNumber);
