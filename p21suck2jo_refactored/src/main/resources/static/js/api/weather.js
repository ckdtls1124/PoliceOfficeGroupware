
///날씨

const weatherList = document.querySelector('.weather-list');
const weatherListCon = document.querySelectorAll('.weather-list .con');

const search = $('#search'); //ajax식 선택자 선택방법

function weatherSearch() {
  weatherFn(search.val()) //ajax식 value사용방법
}

function weatherFn(cityVal) {

  weatherListCon.forEach(el => {
    el.innerText = "";
  });

  // 날씨 api - fontawesome 아이콘
  let weatherIcon = {
    '01': 'fas fa-sun',
    '02': 'fas fa-cloud-sun',
    '03': 'fas fa-cloud',
    '04': 'fas fa-cloud-meatball',
    '09': 'fas fa-cloud-sun-rain',
    '10': 'fas fa-cloud-showers-heavy',
    '11': 'fas fa-poo-storm',
    '13': 'far fa-snowflake',
    '50': 'fas fa-smog'
  };

  let appUrl = `https://api.openweathermap.org/data/2.5/weather?q=${cityVal}&appid=b4e7d16289b18899cecc2f2dcbdc884f`;

  $.ajax({
    url: appUrl,
    dataType: "json",
    type: "GET",
    success: function (result) {
      console.log(result.name);

      let lat = result.coord.lat; //위도
      let lon = result.coord.lon; // 경도

      console.log("위도: " + lat);
      console.log("경도: " + lon);

      console.log(result);

      let $Icon = (result.weather[0].icon).substr(0, 2);
      let $weather_description = result.weather[0].main; //현재 날씨 상태
      let $Temp = Math.floor(result.main.temp - 273.15) + 'º';
      let $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + result.main.humidity + ' %';
      let $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' + result.wind.speed + ' m/s';
      let $city = cityVal;
      let $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + result.clouds.all + "%";
      let $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(result.main.temp_min - 273.15) + 'º';
      let $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(result.main.temp_max - 273.15) + 'º';

      $('.weather_icon').append('<i class="' + weatherIcon[$Icon] +
        ' fa-5x" style="height : 150px; width : 150px;"></i>');
      $('.weather_description').prepend($weather_description);
      $('.current_temp').prepend($Temp);
      $('.humidity').prepend($humidity);
      $('.wind').prepend($wind);
      $('.city').append($city);
      $('.cloud').append($cloud);
      $('.temp_min').append($temp_min);
      $('.temp_max').append($temp_max);

      mapFn(lat, lon);
    }
  })
}

(
  () => {
    weatherFn("seoul")
  }
)();


///카카오지도

function mapFn(lat, lon) {

  var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
      level: 9 // 지도의 확대 레벨
    };

  var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

  // 마커가 표시될 위치입니다 
  var markerPosition = new kakao.maps.LatLng(lat, lon);

  // 마커를 생성합니다
  var marker = new kakao.maps.Marker({
    position: markerPosition
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);

  // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
  // marker.setMap(null);

}