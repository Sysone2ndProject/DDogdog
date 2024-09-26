let address;
document.addEventListener('DOMContentLoaded', () => {
    // 주소 정보 가져오기
    axios.get('/v1/customers/member/location', {
        params: {
            addressId // JSP에서 주소 정보를 가져오는 부분을 대체합니다.
        }
    })
    .then(response => {
        address = response.data; // 받은 데이터 (주소 정보)
        document.getElementById('addressDisplay').textContent = "주소 : "+address;
        loadKakaoMap(address); // 카카오 맵 로드 함수 호출
    })
    .catch(error => {
        console.error('Error fetching the address:', error);
    });

    // 펫 정보 가져오기
    axios.get('/v1/customers/pets', {
        params: {
            customerId // JSP에서 넘어온 customerId 사용
        }
    })
    .then(response => {

        let pets = response.data;
        let rightColumn = document.querySelector('.right-column'); // 카드가 들어갈 영역 선택

        pets.forEach(pet => {
            // 각 펫 정보를 담은 HTML 카드 요소를 생성
            let petCard = `
                        <div class="pet-card pet-item" data-id="${pet.id}">
                            <div class="pet-content">
                                <img src="${pet.petImage}" class="image-preview" alt="${pet.name}">
                                <div class="pet-info">
                                    <div class="pet-info-details">
                                      ${pet.name} ${pet.age}살<br>
                                      ${pet.gender}
                                    </div>
                                    <div class="pet-info-details">
                                         <span class="material-symbols-outlined">
                                           sound_detection_dog_barking
                                          </span>${pet.species} 
                                     </div>
                                      <div class="pet-info-details">
                                          <span class="material-symbols-outlined">
                                           scale
                                           </span> ${pet.weight}kg
                                    </div>
                                </div>
                            </div>
                        </div>
                        `;
            // 생성한 카드 요소를 페이지에 추가
            rightColumn.innerHTML += petCard;
        });

        // 카드 클릭 이벤트 추가
        document.querySelectorAll('.pet-item').forEach(item => {
            item.addEventListener('click', (event) => {
                const petId = item.getAttribute('data-id');
                // petId를 사용하여 요청을 보냄
                window.location.href = `/v1/customers/pets/${petId}`; // 예: 상세 페이지로 이동
            });
        });
    })
    .catch(error => {
        console.error('Error fetching the pets:', error);
    });

    // 예약 스탯 가져오기
    axios.get('/v1/customers/reservation/stats', {
    })
        .then(response => {
            console.log(response.data);
            const { totalReservations, pastReservations, futureReservations,currentReservations } = response.data;

            document.querySelector('.reservations.total').textContent = `${totalReservations}`;
            document.querySelector('.reservations.current').textContent = `${currentReservations}`;
            document.querySelector('.reservations.past').textContent = `${pastReservations}`;
            document.querySelector('.reservations.future').textContent = `${futureReservations}`;
        })
        .catch(error => {
            console.error('Error fetching the address:', error);
        });

    axios.get('/v1/customers/reservation/mostHotel', {
    })
    .then(response => {
        let mostHotel = document.querySelector('.most-hotel');
        let hotel = (response.data);
        //TODO : 구현완료 후 지울예정
        console.log(hotel);

        let hotelCard = `
                       
                            <div class="pet-content">
                                <img src="${hotel.mainImage}" class="image-preview" alt="${hotel.businessName}">
                                <div class="pet-info">
                                    <div class="pet-info-details">
                                      ${hotel.businessName}
                                    </div>
                                    <div class="pet-info-details">
                                      방문횟수 ${hotel.visitCount}회
                                    </div>
                                    <div class="pet-info-details">
                                      번호 : ${hotel.phoneNumber}<br>
                                    </div>
                                    <div class="pet-info-details">
                                      주소 : ${hotel.fullAddress}<br>
                                    </div>
                                      <div class="pet-info-details">
                                        ${hotel.intro} <button onclick="goToHotel(${hotel.hotelId})">    바로가기</button>
                                    </div>
                                </div>
                            </div>
                              `;
        // 생성한 카드 요소를 페이지에 추가
        mostHotel.innerHTML += hotelCard;
    })
    .catch(error => {
        console.error(error);
    });
});

const changeAddress = () => {
    new daum.Postcode({
        oncomplete: async function (data) {
            let fullAddress = ''; // 도로명주소 고정

            if (data.roadAddress !== "") {
                fullAddress = data.roadAddress;
            } else {
                fullAddress = data.autoRoadAddress;
            }

            // 비동기 PUT 요청
            try {
                const response = await axios.put('/v1/customers/location', {
                    id: addressId,
                    fullAddress: fullAddress,
                    sido: data.sido,
                    sigungu: data.sigungu,
                    dong: data.bname
                });

                Swal.fire({
                    title: '주소가 변경되었습니다.',
                    text: `새 주소: ${fullAddress}`,
                    icon: 'success',
                    confirmButtonText: '확인'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // 확인 버튼을 누르면 리디렉션
                        window.location.href = '/v1/customers/members'; // 리디렉션할 페이지로 변경
                    }
                });

            } catch (error) {
                console.error('Error occurred during PUT request:', error);
                Swal.fire({
                    title: '오류',
                    text: '주소 수정 중 오류가 발생했습니다.',
                    icon: 'error',
                    confirmButtonText: '확인'
                });
            }
        }

    }).open();
}

function petAddButton() {
    window.location.href = "/v1/customers/pets/add";
}
function reservationButton(){
    window.location.href="/v1/customers/reservation";
}
const showLocation = () => {
    // SweetAlert2 모달을 사용하여 지도와 수정 버튼 표시
    Swal.fire({
        title: '주소 수정',
        html: `
            <h2>${address}</h2>
            <br>
            <div id="addressMap"></div>
            <button id="findAddressBtn" class="swal2-confirm swal2-styled">주소 수정</button>
            <button id="cancelBtn" class="swal2-cancel swal2-styled">취소</button>
        `,
        showConfirmButton: false, // 기본 확인 버튼 숨기기
        didOpen: () => {
            // 여기서 addressMap이 완전히 로드되었는지 확인 후 지도 로드
            const mapContainer = document.getElementById('addressMap');
            if (mapContainer) {
                // 카카오 지도 로드
                loadKakaoMap(address, 'addressMap'); // addressMap을 ID로 전달
            } else {
                console.error("Map container not found in modal!");
            }

            // 수정 버튼 클릭 시 findAddress 함수 호출
            document.getElementById('findAddressBtn').addEventListener('click', () => {
                changeAddress(); // 주소 수정 함수 호출

            });

            // 취소 버튼 클릭 시 모달 닫기
            document.getElementById('cancelBtn').addEventListener('click', () => {
                Swal.close(); // 모달 닫기
            });
        }
    });
};

const loadKakaoMap = (address, mapId) => {
    kakao.maps.load(() => {
        const mapContainer = document.getElementById(mapId); // 동적으로 전달된 mapId로 지도 표시할 div를 가져옴
        if (!mapContainer) {
            console.error('Map container not found');
            return;
        }

        const mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 초기 중심좌표
            level: 3 // 지도의 확대 레벨
        };

        // 지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);

        // 주소-좌표 변환 객체를 생성합니다
        const geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(address, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
                const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            } else {
                alert('주소를 찾을 수 없습니다.');
            }
        });
    });
};
function goToHotel(id) {
    window.location.href = '/v1/customers/hotels/'+id;
}