function cancelReservation(reservationId) {
    Swal.fire({
        title: '정말 예약을 취소하시겠습니까?',
        text: "이 작업은 되돌릴 수 없습니다!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '예',
        cancelButtonText: '아니오'
    }).then((result) => {
        if (result.isConfirmed) {
            axios.patch(`/v1/customers/reservation/` + reservationId)
                .then(response => {
                    // Handle success response (e.g., show success message, reload page)
                    Swal.fire(
                        '취소 완료!',
                        '예약이 취소되었습니다.',
                        'success'
                    ).then(() => {
                        // Optional: Reload the page or update the UI
                        window.location.reload();
                    });
                })
                .catch(error => {
                    // Handle error response
                    Swal.fire(
                        '오류 발생!',
                        '예약 취소 중 문제가 발생했습니다. 다시 시도해 주세요.',
                        'error'
                    );
                });
        }
    });
}

function viewDetails(reservationId) {
    axios.get(`/v1/customers/roomChoice/`+ reservationId)
    .then(response => {
        const reservationDetails = response.data;
        showReservationDetails(reservationDetails);
    })
    .catch(error => {
        console.error("Error fetching reservation details:", error);
        Swal.fire('Error!', '예약 정보를 가져오는 데 실패했습니다.', 'error');
    });
}

function showReservationDetails(details) {
    const modalContent = details.map(room => `
        <div class="room-detail">
            <h3>방 정보</h3>
            <p>방 이미지: <img src="${room.roomImage}" alt="${room.intro}" style="width:100px;"/></p>
            <p>방 이미지: ${room.intro}</p>
            <p>방 등급: ${room.grade}</p>
            <p>예약 기간: ${room.startDate} - ${room.endDate}</p>
            <p>가격: ${room.nowPrice} 원</p>
        </div>
    `).join(''); // 각 방 정보를 HTML로 변환 후 문자열로 결합

    const totalDetailsContent = `
        <h2>예약 상세 정보</h2>
        <p>총 ${details.length}개 방 예약됨</p>
        <div>${modalContent}</div> <!-- 방 정보 표시 -->
    `;

    Swal.fire({
        title: '예약 상세 정보',
        html: totalDetailsContent,
        showCloseButton: true,
        focusConfirm: false,
    });
}