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
