
const strSrch = document.querySelector('.strSrch');
document.querySelector('.findBusRouteList').addEventListener('click', () => {

    let busAPIJava = `/api/bus/busList/${strSrch.value}`;

    fetch(busAPIJava)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            //            data.msgBody.itemList.forEach((element) => {
            //                console.log(element.stationNm)
            //            });
        });
});



