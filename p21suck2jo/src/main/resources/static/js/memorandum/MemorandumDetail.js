
// 현재 접속한 결재 문서의 ID를 sessionStorage 에 저장
const currentMemoId = document.querySelector('.currentMemoId');
sessionStorage.setItem("currentMemoId", currentMemoId.value);





// 특정 파일을 삭제

document.querySelectorAll(".deleteFile").forEach((fileEl, fileIndex) => {
    fileEl.addEventListener('click', (e) => {
        e.preventDefault();
        document.querySelectorAll(".numberOfFile").forEach((noEl, noIndex) => {

            if (fileIndex === noIndex) {
                $.ajax({
                    url: "/memo/file/delete/" + noEl.value,
                    method: "get",
                    data: null,
                    success: function (successData) { location.href = "/memo/memoDetail/"+sessionStorage.getItem('currentMemoId') }
                })
            }
            
        })
    });
});