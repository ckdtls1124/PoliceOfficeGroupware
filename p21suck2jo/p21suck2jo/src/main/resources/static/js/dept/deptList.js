const deptInsertBtn = document.querySelector(".dept-insert-btn");
const deptInsert = document.querySelector(".dept-insert");
const deptHide = "deptHide";

function showDeptInsert(e) {
	deptInsert.classList.toggle("deptHide");
}

deptInsertBtn.addEventListener("click", showDeptInsert);


function confirmDelete() {
  if (confirm("※경고 : 해당 부서를 삭제하시겠습니까? 이 작업은 번복할 수 없습니다.")) {
    document.forms[0].submit();
  } else {
    return false; //
  }
}

//function editRow(button){
//    var row = button.closest("tr");
//    var cells = row.getElementsByTagName("td");
//
//    for(var i = 0 ; i <cells.length - 1 ; i++){
//    var cell = cells[i];
//    var value = cell.innerText;
//    cell.innerHTML = "<input type='text' value='" + value + "'>";
//    }
//}