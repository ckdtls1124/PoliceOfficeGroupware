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
                    success: function (successData) { location.href = "/memo/updateMemoPage/"+sessionStorage.getItem('currentMemoId') }
                })
            }

        })
    });
});