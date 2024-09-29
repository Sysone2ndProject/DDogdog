const needLogin = () => {
  Swal.fire({
    title: '로그인 후 이용해주세요',
    icon: 'success',
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = '/v1/owners/login';
    }
  });
}
