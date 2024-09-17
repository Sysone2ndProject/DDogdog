document.addEventListener("DOMContentLoaded", () => {
    // axios를 이용한 비동기 요청
    axios.get('/v1/customers/myPage/location', {
        params: {
            addressId: addressId // JSP에서 주소 정보를 가져오는 부분을 대체합니다.
        }
    })
        .then(response => {
            const address = response.data; // 받은 데이터 (주소 정보)
            document.getElementById('address-display').textContent = address;
            loadKakaoMap(address);
        })
        .catch(error => {
            // 오류가 발생한 경우 처리
            console.error('Error fetching the address:', error);
        });
});

// 카카오 지도 API가 로드된 후에 호출되는 함수
const loadKakaoMap = address => {
    kakao.maps.load(() => {
        const mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
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
}
