
// 업무 시작(업무 종료) 버튼 클릭 시, 업무 종료(업무 시작)으로 변경
const workStatusCon = document.querySelector('#workStatusCon');
const workStatus = document.querySelector('#workStatus');


workStatusCon.addEventListener('click', () => {

    if (workStatus.innerHTML === '업무 시작') {
        workStatus.innerHTML = '업무 종료';

        // 업무 시작(업무 종료) 버튼 클릭 시, 클릭이 이루어진 시간 DB에 입력
        $.ajax({
            url: "/attend/startWorking",
            method: "get",
            data: null,
            success : function(successData){
                alert("출근이 완료되었습니다.")
                setCalendar('출근', successData, successData);
            }
        })

        // 업무 시작 버튼 클릭 시, full calendar에 당일로 시간이 들어감.





    } else {
        workStatus.innerHTML = '업무 시작';

        // 업무 시작(업무 종료) 버튼 클릭 시, 클릭이 이루어진 시간 DB에 입력
        $.ajax({
            url: "/attend/endWorking",
            method: "get",
            data: null,
            success: function (successData) {
                alert("퇴근이 완료되었습니다.")
                setCalendar('퇴근', successData, successData);
            }
        })

    }

});



// Full Calendar에 CR
// DB 데이터 Create
function setCalendar(content, start, end) {
    $.ajax({
        url: "/api/calendar",
        method: "POST",
        dataType: "json",
        async: false,
        data: {
            content: content,
            start: start,
            end: end
        }
    })
        .done(function (data) {
            getCalendar(calendar.getDate())
            calendar.render();
        })
        .fail(function (xhr, status, errorThrown) {
            console.log("오류");
        })
        .always(function (xhr, status) {
            console.log("완료");
        });

}

