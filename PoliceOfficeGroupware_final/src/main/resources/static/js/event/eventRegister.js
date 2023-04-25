const open = document.querySelector('.map-open');
const close = document.querySelector('.map-close');
const eventMap = document.querySelector('.map-wrap');

function mapView() {
  open.addEventListener("click", function () {
    eventMap.classList.remove('hidden');
  });
  close.addEventListener("click", function () {
    eventMap.classList.add('hidden');
  })
}

mapView();