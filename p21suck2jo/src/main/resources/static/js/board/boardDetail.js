const writeBtn = document.querySelector(".write-btn");
const replyWrite = document.querySelector(".reply-write");
const boardHide = "boardHide";

function showReplyWrite(e) {
	e.preventDefault();

	replyWrite.classList.toggle("boardHide");
}

writeBtn.addEventListener("click", showReplyWrite);
