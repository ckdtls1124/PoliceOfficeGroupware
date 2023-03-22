const deptInsertBtn = document.querySelector(".dept-insert-btn");
const deptInsert = document.querySelector(".dept-insert");
const deptHide = "deptHide";

function showDeptInsert(e) {
	deptInsert.classList.toggle("deptHide");
}

deptInsertBtn.addEventListener("click", showDeptInsert);