
// 특정 결재문서를 삭제 (삭제 하기 전, 삭제하겠냐고 물어보기)

document.querySelectorAll(".deleteMemo").forEach((memoEl, memoIndex) => {
    memoEl.addEventListener('click', (e) => {
        e.preventDefault();

        document.querySelectorAll('.memoId').forEach((memoId, memoIdIndex) => {
            if(memoIndex === memoIdIndex){
                $.ajax({
                    url: "/memo/delete/" + memoId.value,
                    method: "get",
                    data: null,
                    success: function (successData)
                })
            }
        });
    });
})