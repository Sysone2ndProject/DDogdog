let currentSlide = 0;
const cells = document.querySelectorAll('.cell');
const totalSlides = cells.length; //총 셀 갯수만큼 이동

function updateCarousel() {
  const offset = -currentSlide * 100; // 슬라이드 이동 거리 계산
  cells.forEach(cell => {
    cell.style.transform = `translateX(${offset}%)`;
  });
}

function nextSlide() {
  if (currentSlide < totalSlides - 1) {
    currentSlide++;
  } else {
    currentSlide = 0; // 마지막 슬라이드에서는 처음으로 돌아가기
  }
  updateCarousel();
}

function prevSlide() {
  if (currentSlide > 0) {
    currentSlide--;
  } else {
    currentSlide = totalSlides - 1; // 첫 슬라이드에서 마지막으로 돌아가기
  }
  updateCarousel();
}
