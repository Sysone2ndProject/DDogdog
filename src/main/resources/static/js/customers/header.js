document.addEventListener("DOMContentLoaded", () => {
  const currentPage = window.location.pathname;

  if (currentPage === '/v1/customers') {
    document.querySelector('.header').classList.add('color-tp');
  } else {
    document.querySelector('.header').classList.add('color-pink');
  }
})
