function dropMenu() {
    const menu = document.getElementById('drop-menu');
    const isVisible = menu.style.display === 'block';

    if (isVisible) {
        menu.style.display = 'none';
    } else {
        menu.style.display = 'block';
    }
}

function dropMenuLogin() {
    const menu = document.getElementById('drop-menu-login');
    const isVisible = menu.style.display === 'block';

    if (isVisible) {
        menu.style.display = 'none';
    } else {
        menu.style.display = 'block';
    }
}