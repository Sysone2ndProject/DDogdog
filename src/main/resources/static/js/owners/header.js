const dropMenu = () => {
  const menu = document.getElementById('drop-menu');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}

const dropMenuLogin = () => {
  const menu = document.getElementById('drop-menu-login');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}

const dropSubMenu = () => {
  const menu = document.getElementById('hotel-sub-menu');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}

const dropSubMenuLogin = () => {
  const menu = document.getElementById('hotel-sub-menu-login');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}

const toggleDropSubMenuLogin = () => {
  const menu = document.getElementById('hotel-toggle-sub-menu-login');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}

const toggleDropSubMenu = () => {
  const menu = document.getElementById('hotel-toggle-sub-menu');
  const isVisible = menu.style.display === 'block';

  if (isVisible) {
    menu.style.display = 'none';
  } else {
    menu.style.display = 'block';
  }
}