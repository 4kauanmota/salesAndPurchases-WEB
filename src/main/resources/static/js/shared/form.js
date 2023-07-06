const img = document.querySelector(".background img");

let actualImage;

setInterval(() =>{
  actualImage = Math.floor((Math.random() * 10) + 1);
  img.setAttribute('src', `/img/shared/form/img${actualImage}.jpg`);
}, 5000);