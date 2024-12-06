let list = document.querySelector('.slider .list');
let items = document.querySelectorAll('.slider .list .item');
let dots = document.querySelectorAll('.slider .dots li');
let prev = document.getElementById('prev');
let next = document.getElementById('next');

const productContainers = [...document.querySelectorAll('.product-container')];
const nxtBtn = [...document.querySelectorAll('.nxt-btn')];
const preBtn = [...document.querySelectorAll('.pre-btn')];


let active = 0;
let lengthItems = items.length;

dots[0].classList.add('active');

next.onclick = function() {
	if (active + 1 >= lengthItems) {
		active = 0;
	} else {
		active = active + 1;
	}
	reloadSlider();
}
prev.onclick = function() {
	if (active - 1 < 0) {
		active = lengthItems - 1;
	} else {
		active = active - 1;
	}

	reloadSlider();
}
let refreshSlider = setInterval(() => { next.click() }, 4000)
function reloadSlider() {
	let checkLeft = items[active].offsetLeft;
	list.style.left = -checkLeft + 'px';

	let lastActiveDot = document.querySelector('.slider .dots li.active');
	if (lastActiveDot) {
		lastActiveDot.classList.remove('active');
	}
	dots[active].classList.add('active');
	clearInterval(refreshSlider);
	refreshSlider = setInterval(() => { next.click() }, 4000)
}
dots.forEach((li, key) => {
	li.addEventListener('click', function() {
		active = key;
		reloadSlider();
	})
})

productContainers.forEach((item, i) => {
	let containerDimensions = item.getBoundingClientRect();
	let containerWidth = containerDimensions.width;

	nxtBtn[i].addEventListener('click', () => {
		item.scrollLeft += containerWidth;
	})

	preBtn[i].addEventListener('click', () => {
		item.scrollLeft -= containerWidth;
	})
})

