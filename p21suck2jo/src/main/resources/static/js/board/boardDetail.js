const writeBtn = document.querySelector(".write-btn");
const replyWrite = document.querySelector(".reply-write");
const boardHide = "boardHide";

function showReplyWrite(e) {
	e.preventDefault();

	replyWrite.classList.toggle("boardHide");
}

writeBtn.addEventListener("click", showReplyWrite);

//const updateBtn=document.querySelector(".update-btn");
//const replyUpdate=document.querySelector(".reply-update");
//const updateHide="updateHide";
//
//function showReplyUpdate(e){
//    e.preventDefault();
////    e.target.nextElementSibling.classList.toggle("updateHide");
//    replyUpdate.classList.toggle("updateHide");
//}
//
//
//updateBtn.addEventListener("click",showReplyUpdate);

const updateBtns=document.querySelectorAll(".update-btn");
const replyUpdates=document.querySelector(".reply-update");


updateBtns.forEach((el,idx)=>{
  el.addEventListener("click",showReplyUpdate2);
});

function showReplyUpdate2(e){
    e.preventDefault();
    e.target.nextElementSibling.classList.toggle("updateHide");
//    replyUpdate.classList.toggle("updateHide");
}



