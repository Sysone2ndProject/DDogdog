function getSignUpForm() {
  const button = document.querySelector('#signUp');
  button.addEventListener("click", function () {
    window.location.href = '/v1/owners/signup'; //owners 메인페이지 연결
  });
}