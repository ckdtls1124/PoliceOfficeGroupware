// 영화 제목 출력
const movieTitles = document.querySelector('.screen-title-btn');
const movieContentsCon = document.querySelector('.movie-content');


function getMovieList(){
    let movieNames = new Array();
    movieNames = [];
    const key = 'd5ed5c2a608d3ca08ee86c8fae2ce639';
    fetch(`http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=${key}`)
    .then((response) => response.json())
    .then((data) => {
        // data.movieListResult.movieList.forEach((element) => {
        //     return element;
        // })
        data.movieListResult.movieList.forEach((element) => {
            movieNames.push(element.movieNm);
        });
    })

    movieTitles.addEventListener('click', (e)=>{
        
        movieContentsCon.innerHTML = movieContentsCon.innerHTML + 
        `<ul>
            <li>${movieNames[0]}</li>
            <li>${movieNames[1]}</li>
            <li>${movieNames[2]}</li>
        </ul>`;
    })


    // return movieNames;
    
}

(()=>{
    getMovieList();
})()

