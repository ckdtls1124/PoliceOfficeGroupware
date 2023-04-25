let index_user = {
	init: function() {
		$("#btn-find").on("click", () => {
			this.find();
		});
	},
	find: function() {
		LoadingWithMask();
		
		let data = {
//		username: $("#username").val(),
//        email: $("#email").val()
			policeNumber: $("#policeNumber").val(),
			email: $("#email").val()	
		};
		
		$.ajax({
			type: "POST",
			url: "/smtppwSearch",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
		}).done(function(resp) {
			if (resp.status == 400) {
				if (resp.data.hasOwnProperty('email')) {
					$('#email').text(resp.data.valid_email);
					$('#email').focus();
				} else {
					$('#email').text('');
				}
				
				if (resp.data.hasOwnProperty('policeNumber')) {
					$('#policeNumber').text(resp.data.valid_username);
					$('#policeNumber').focus();
				} else {
					$('#policeNumber').text('');
				}
				
				closeLoadingWithMask();
			} else {				
				alert("임시 비밀번호가 발송되었습니다.");
				location.href = "/login";
			}
		}).fail(function(error) {
			console.log(error);
		});
	}
}
index_user.init();

function LoadingWithMask() {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;

    //화면에 출력할 마스크를 설정해줍니다.
    var mask    = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var spinner = "<div id='spinner' style='position: absolute; top: 45%; left: 50%; margin: -16px 0 0 -16px; display: none; color: #4dff93;' class='spinner-border'></div>";

    //화면에 레이어 추가
    $('body')
        .append(mask)

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth,
            'height': maskHeight,
            'opacity' : '0.3'
    });

    //마스크 표시
    $('#mask').show();

    //로딩중 이미지 표시
    $('body').append(spinner);
    $('#spinner').show();
}

function closeLoadingWithMask() {
	$('#mask, #spinner').hide();
	$('#mask, #spinner').empty();
}
