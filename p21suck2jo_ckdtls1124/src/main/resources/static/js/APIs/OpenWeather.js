
function weatherFn(cityName){

    let appUrl = `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=47349a9ae570d056f7c68d2d1a61988e`;

    $.ajax({
        url: appUrl,
        method: "GET",
        dataType: "JSON",
        success: function(successData){
            console.log(successData);
        }
    })

};

(()=>{
    weatherFn("Seoul");
})();