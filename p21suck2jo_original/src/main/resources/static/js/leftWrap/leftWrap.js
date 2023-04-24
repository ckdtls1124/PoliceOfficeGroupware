// 현재 시간 알려줌
const currentTime = document.querySelector('.currentTime');

function showCurrentTime(){

    var current = new Date();

    currentTime.innerHTML = `${current.getHours().toString().padStart(2, '0')} : ${current.getMinutes().toString().padStart(2, '0')} : ${current.getSeconds().toString().padStart(2, '0')}`;


}

showCurrentTime();

(()=>{
    setInterval(showCurrentTime, 1000);
})()