const boxCon = document.querySelector(".box-con");
const sceenDetail = document.querySelector(".box-con .sceen-detail");

const key = "00253d7a366bf8786ff9996a91b3f4c3";

let html1 = "";

function boxofficeFn() {
	//http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=&targetDt=
	let dailyBoxOfficeList = "searchDailyBoxOfficeList.json";
	let today = new Date().toISOString().substring(0, 10).replace(/-/g, '') - 1;
	let targetDt = today.toString();
	let itemPerPage = "10";
	let apiUrl = `http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/${dailyBoxOfficeList}?key=${key}&targetDt=${targetDt}&itemPerPage=${itemPerPage}`;

	fetch(apiUrl)
		.then((response) => response.json())
		.then((result) => {
			console.log(result, typeof result);
			let movieListResult = result.boxOfficeResult;
			let movieList = movieListResult.dailyBoxOfficeList;

			console.log(movieList, typeof movieList);

			// let titleCon = movieListResult.boxofficeType;
			// title.innerText = titleCon; // 텍스트 경우

			html1 += `<div class="detail-header">
			        <ul>
			          <li>순번</li>
			          <li>제목</li>
			          <li>개봉일</li>
			          <li>누적 관객수</li>
			        </ul>
			    </div>`;
			html1 += `<div class="detail-con">`;
			movieList.forEach((el) => {
				console.log(el);
				html1 += `<ul>`;
				html1 += `<li><span onclick="sceenDeailFn(${el.movieCd})">${el.rank}</span></li>`;
				html1 += `<li>${el.movieNm}</li>`;
				html1 += `<li>${el.openDt}</li>`;
				html1 += `<li>${el.audiAcc}</li>`;
				html1 += `</ul>`;
			});
			html1 += `</div>`;
			// console.log(html1);
			sceenDetail.innerHTML = html1; //HTML innerHTML
		});
}

const popup = document.querySelector(".popup");
const close = document.querySelector(".close");
const popupCon = document.querySelector(".popup-con");
const popupTitle = document.querySelector(".popup-title");
const mLocal = document.querySelector(".mLocal");
const mGenre = document.querySelector(".mGenre");
const mAudits = document.querySelector(".mAudits");
const mSday = document.querySelector(".mSday");
const mTime = document.querySelector(".mTime");
const mActors = document.querySelector(".mActors");

function sceenDeailFn(movieCode) {
	popup.classList.add("popup-ani");
	close.classList.add("close-ani");

	let movieSerchJSON = "movie/searchMovieInfo.json";
	let movieCd = movieCode; //   영화 코드
	const url =
		"http://www.kobis.or.kr/kobisopenapi/webservice/rest/" +
		movieSerchJSON +
		"?key=" +
		key +
		"&movieCd=" +
		movieCd;

	fetch(url)
		.then((response) => response.json())
		.then(function (msg) {
			//아래부터는 html로 가져오기 위한 코드
			console.log(msg);

			let movieDetailListResult = msg.movieInfoResult;
			let movieDetailList = movieDetailListResult.movieInfo;

			popupTitle.innerText = movieDetailList.movieNm;
			mLocal.innerText = movieDetailList.nations[0].nationNm;
			mGenre.innerText = movieDetailList.genres[0].genreNm;
			mAudits.innerText = movieDetailList.audits[0].watchGradeNm;
			mSday.innerText = movieDetailList.openDt;
			mTime.innerText = movieDetailList.showTm + "분";

			let txt = "";
			movieDetailList.actors.forEach((el) => {
				txt += el.peopleNm + "  ";
			});

			mActors.innerText = txt;
		});
}

close.addEventListener("click", () => {
	popup.classList.remove("popup-ani");
	close.classList.remove("close-ani");
});

(() => {
	boxofficeFn();
})();
