
'https://cors-anywhere.herokuapp.com/corsdemo'

function busAPIUse() {

    const serviceKey = 'ctqGZgF9DB7df8LVF3SNgwfCAKkD9N84yh3%2BNkdx3iK64rz%2FemZ8IO7GruEb8bIaH4ecQeAJRs4eJjoax%2BwnUQ%3D%3D';

    fetch(`https://cors-anywhere.herokuapp.com/http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute?serviceKey=${serviceKey}&busRouteId=100100025&resultType=json`)
        .then((response) => response.json())
        .then((data) => { 
            data.msgBody.itemList.forEach((element) => {
                console.log(element.stationNm)
            });
         });

};

(()=>{
    busAPIUse()
})();