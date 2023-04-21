const key = "0SLXqFB%2BK89GnZ0bmJO7Q%2FShWT0j2CgBy5tkQA2vZ6%2FfoIVb2JW2oLjefZauR3uw4hKmBAXvUEC3B3%2BKFIlYtw%3D%3D";
const search = $('#search');
const lostGoods = $('.lost-list');


function lostDateSearch() {
  let selectDate = search.val().replace('-', '').replace('-', '');
  console.log('해당 날짜의 분실품 목록 조회 >>> ' + selectDate);

  lostGoodsFn(selectDate);
}

function lostGoodsFn(lostDate) {

  let apiUrl = `
http://apis.data.go.kr/1320000/LostGoodsInfoInqireService/getLostGoodsInfoAccToClAreaPd?
serviceKey=${key}&START_YMD=${lostDate}&END_YMD=${lostDate}&PRDT_CL_CD_01=PRA000&PRDT_CL_CD_02=PRA300
&LST_LCT_CD=LCA000&pageNo=1&numOfRows=20`;

  $.ajax({

    url: apiUrl,
    dateType: "xml",
    type: "GET",

    success: function (result) {

      console.log(result)

      let response = result.getElementsByTagName('response');
      let htmlInput = "";

      console.log(response, typeof response)

      Array.from(response).forEach(el => {
        //forEach로 response를 순회해줘야 response 태그 안의 자식 태그들에 접근이 가능함

        let responseChilds = el.childNodes; //childNodes로 접근하면 배열 변환할 필요가 없음
        console.log(responseChilds, typeof responseChilds)

        //자식태그에서 바로 다시 자식태그로 접근할 수 없음 다시 변수에 담아 접근해보기

        let itemList = responseChilds[1].childNodes[0].children;
        console.log(itemList, typeof itemList)

        Array.from(itemList).forEach(el => {
          //item이 가지고 있는 요소들을 순회(실질적인 정보/분실번호, 품목명 등등)
          let itemEl = el.childNodes;
          // console.log(itemEl, typeof itemEl)

          htmlInput += "<ul>";

          for (let i = 0; i <= 5; i++) {
            var lostData = itemEl[i].textContent
            htmlInput += `<li>${lostData}</li>`;
          }

          htmlInput += "</ul >";
          // console.log(htmlInput + " lostData")
        })

      })
      lostGoods.html(htmlInput);
    }

  })

}


(() => {

  let firstDate = new Date()

  console.log("가장 처음 조회할 날짜 >>> " + dateFormatting(firstDate))

  function dateFormatting(date) {

    let month = date.getMonth() + 1;
    let day = date.getDate() - 1;

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;

    document.getElementById('search').value = date.getFullYear() + "-" + month + "-" + day
    return date.getFullYear() + month + day;

  }

  lostGoodsFn(dateFormatting(firstDate))

})()