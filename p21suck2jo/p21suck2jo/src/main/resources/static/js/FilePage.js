
function ajaxDeleteFileFn(e){
    e.preventDefault();

    let numberOfFile = document.querySelector(".numberOfFile");

    // th:href="|/delete/${file.memorandumFileId}|"
    $.ajax({
        url: "/delete/"+numberOfFile.value,
        method: "get",
        data: null,
        success: function(successData)
        {location.href="/"}
    })


}

document.querySelector(".deleteFile").addEventListener('click', ajaxDeleteFileFn);