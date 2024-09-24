document.addEventListener('DOMContentLoaded', () => {
    // 이용 내역 버튼 클릭 이벤트 리스너 추가
    const reservationButton = document.getElementById('reservationButton');
    if (reservationButton) {
        reservationButton.addEventListener('click', () => {
            window.location.href = '/v1/customers/reservation';
        });
    }

    // 주소 정보 가져오기
    axios.get('/v1/customers/member/location', {
        params: {
            addressId: addressId // JSP에서 주소 정보를 가져오는 부분을 대체합니다.
        }
    })
    .then(response => {
        const address = response.data; // 받은 데이터 (주소 정보)
        document.getElementById('address-display').textContent = address;
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
        console.log(response.data); // 받은 데이터 (펫 정보)
        let pets = response.data;
        let rightColumn = document.querySelector('.right-column'); // 카드가 들어갈 영역 선택

        pets.forEach(pet => {
            // 각 펫 정보를 담은 HTML 카드 요소를 생성
            let petCard = `
                        <div class="pet-card pet-item" data-id="${pet.id}">
                            <div class="pet-content">
                                <img src="${pet.petImage}" class="image-preview" alt="${pet.name}">
                                <div class="pet-info">
                                    <span class="material-icons-outlined">
                                      badge
                                    </span>
                                      ${pet.name}
                                    <span class="material-symbols-outlined">
                                       sound_detection_dog_barking
                                    </span>${pet.speciesId} 
                                      <span class="material-symbols-outlined">
                                       scale
                                    </span> ${pet.weight}
${pet.age}
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
});

