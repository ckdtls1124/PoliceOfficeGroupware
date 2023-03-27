const writeBtn = document.querySelector(".write-btn");
const replyWrite = document.querySelector(".reply-write");
const writeCancelBtn = document.querySelector(".writeCancel");
const boardHide = "boardHide";

function showReplyWrite(e) {
	e.preventDefault();

	replyWrite.classList.remove("boardHide");
}

writeBtn.addEventListener("click", showReplyWrite);

function hideReplyWrite() {
	replyWrite.classList.add("boardHide");
}

writeCancelBtn.addEventListener("click", hideReplyWrite);


const updateBtns = document.querySelectorAll(".update-btn");
const replyUpdates = document.querySelector(".reply-update");
const updateCancelBtn = document.querySelectorAll(".updateCancel");
const updateHide = "updateHide";

updateBtns.forEach((el, idx) => {
	el.addEventListener("click", showReplyUpdate);
});

function showReplyUpdate(e) {
	e.preventDefault();
	e.target.nextElementSibling.classList.remove("updateHide");
	console.log(e.target.nextElementSibling)
}

function hideReplyUpdate(e) {
	e.target.closest(".reply-update").classList.add("updateHide");
}

updateCancelBtn.forEach((el, idx) => {
	el.addEventListener("click", hideReplyUpdate);
});


