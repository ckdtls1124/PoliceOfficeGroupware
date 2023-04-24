
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
                    success: function (successData)  { location.href = "/memo/memoDetail/"+sessionStorage.getItem('currentMemoId') }
                })
            }
            
        })
    });
});

// Memo 승인/반려 처리
const approveMemo = document.querySelectorAll('.approveMemo');
const memorandumId = document.querySelector('.currentMemoId');

approveMemo.forEach((el, index)=>{
    el.addEventListener('click', (e)=>{
        e.preventDefault();
        
        if(e.currentTarget === e.target){
            console.log("This is "+index);

            $.ajax({
                url: "/memo/approve",
                method: "post",
                dataType: "JSON",
                data: {"approveNum" : index,
                        "currentMemoId" : memorandumId.value},
                success: function(successData){
                    if(successData == '0'){
                        confirm("승인 하시겠습니까?");
                    } else {
                        confirm("반려 하시겠습니까?");
                    }
                }
            })
        }     
    });
});



