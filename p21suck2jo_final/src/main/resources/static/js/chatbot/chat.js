$(function () {
  $("#question").keyup(questionKeyuped);
});
function openChat() {
  setConnectStated(true);// 보이기
  connect();  // 연결
}

function showMessage(message) {
  $("#chat-content").append(message);
  $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

function setConnectStated(isTrue) {
  if (isTrue) {//true
    $("#btn-chat-open").hide();
    $("#chat-disp").show();
  } else {
    $("#btn-chat-open").show();
    $("#chat-disp").hide();
  }
  // 화면 초기화
  $("#chat-content").html("");
}

function disconnect() {
  setConnectStated(false);  // 연결 종료
  console.log("Disconnected");
}
// 연결
function connect() {
  sendMessageForContact("안녕");
}

function sendMessageForContact(message) {
  $.ajax({
    url: "/goChatbot",
    type: "post",
    data: { message: message },
    success: function (responsedHtml) {
      showMessage(responsedHtml);
    }
  });
}

function sendMessageForMovieDailyBoxOffice(message) {
  $.ajax({
    url: "/findMovieDailyBoxOffice",
    type: "post",
    data: { dateAndTime: message },
    success: function (responsedHtml) {
      showMessage(responsedHtml);
    }
  });
}

function sendMessageForMovieComingSoon(message) {
  $.ajax({
    url: "/findMovieComingSoon",
    type: "post",
    data: { term: message },
    success: function (responsedHtml) {
      showMessage(responsedHtml);
    }
  });
}

function inputTagString(text) {
  var now = new Date();
  var ampm = (now.getHours() > 11) ? "오후" : "오전";
  var time = ampm + now.getHours() % 12 + ":" + now.getMinutes();
  var message = `
		<div class="user">
			<div class="message">
				<div class="part1">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
  return message;
}
//메뉴클릭시 메뉴 텍스트 화면에 표현 
function menuclicked(el) {
  var text = $(el).text().trim();
  var fToken = $(el).siblings(".f-token").val();
  console.log("-----> fToken:" + fToken + "----");
  var message = inputTagString(text);
  showMessage(message);
}

//엔터가 입력이되면 질문을 텍스트 화면에 표현 
function questionKeyuped(event) {
  if (event.keyCode != 13) return;
  btnMsgSendClicked()
}

//전송버튼 클릭이되면 질문을 텍스트 화면에 표현
function btnMsgSendClicked() {
  let question = document.querySelector('#question').value.trim();

  if (question == "" || question.length < 2) return;

  // 영화 API를 사용하여, 박스오피스 조회를 위해, yyyyMMdd 입력을 분류한다.
  if (!isNaN(question)) {
    sendMessageForMovieDailyBoxOffice(question);
  } 
  // 영화  API를 사용하여, 이번/다음 주 개봉 예정작을 보여주기 위해, '이번', '다음' 키워드를 분류한다.
  else if (question.includes("이번") || question.includes("다음")) {
    sendMessageForMovieComingSoon(question);
  } else {
    //그외 메세지 서버로 전달
    sendMessageForContact(question);
  }



  var message = inputTagString(question);
  showMessage(message);//사용자가 입력한 메세지 채팅창에 출력
  $("#question").val("");//질문 input 리셋
}
