function checkId() {
  const id = document.getElementById("id").value;
  if (!id) {
    alert("아이디를 입력해 주세요.");
    return;
  }

  axios.get(`/v1/owners/${id}`)
  .then(response => {
    if (response.data) {
      alert("이미 존재하는 아이디입니다.");
    } else {
      alert("사용 가능한 아이디입니다.");
    }
  })
  .catch(error => {
    console.error("Error:", error);
    alert("아이디 중복 확인 중 오류가 발생했습니다.");
  });
}

function numberCheck() {
  const accountNumberInput = document.getElementById('accountNumber');
  const numericPattern = /^[0-9]*$/;
  if (!numericPattern.test(accountNumberInput.value)) {
    alert("숫자만 입력해주세요");
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
    alert("회원가입을 축하드립니다.");
    window.location.href = '/v1/owners'; //owners 메인페이지 연결
  })
  .catch(error => {
    alert('아이디값을 확인해 주세요');
  });
}
