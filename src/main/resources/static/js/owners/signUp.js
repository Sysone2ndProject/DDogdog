function checkId() {
  const id = document.getElementById("id").value;
  if (!id) {
    Swal.fire({
      title: '아이디를 입력해 주세요',
      icon: 'warning',
    })
    return;
  }

  axios.get(`/v1/owners/${id}`)
  .then(response => {
    if (response.data) {
      Swal.fire({
        title: '이미 존재하는 아이디입니다.',
        icon: 'error',
      })
    } else {
      Swal.fire({
        title: '사용 가능한 아이디입니다.',
        icon: 'success',
      })
    }
  })
  .catch(error => {
    console.error("Error:", error);
    Swal.fire({
      title: '다시 한번 시도해 보세요',
      text: '아이디 중복 확인 중 오류가 발생했습니다.',
      icon: 'error',
    })
  });
}

function numberCheck() {
  const accountNumberInput = document.getElementById('accountNumber');
  const numericPattern = /^[0-9]*$/;
  if (!numericPattern.test(accountNumberInput.value)) {
    Swal.fire({
      title: '숫자만 입력 가능합니다.',
      icon: 'warning',
    })
    accountNumberInput.value = accountNumberInput.value.replace(/[^0-9]/g, '');
  }
}

function signUp() {
  const form = document.getElementById('signUpForm');
  const formData = new FormData(form);

  axios.post(form.action, formData, {
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
  .then(response => {
    Swal.fire({
      title: '회원 가입을 축하드립니다!',
      icon: 'success',
    })
    window.location.href = '/v1/owners'; //owners 메인페이지 연결
  })
  .catch(error => {
    Swal.fire({
      title: '아이디 값을 다시 한번 확인해 주세요.',
      icon: 'error',
    })
  });
}
