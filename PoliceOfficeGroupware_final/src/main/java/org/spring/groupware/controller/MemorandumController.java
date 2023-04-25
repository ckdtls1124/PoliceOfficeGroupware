package org.spring.groupware.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupware.constructor.MemorandumCRUD;
import org.spring.groupware.dto.*;
import org.spring.groupware.entity.MemoApprovedMember;
import org.spring.groupware.entity.PoliceEntity;
import org.spring.groupware.service.MemorandumFileService;
import org.spring.groupware.service.MemorandumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemorandumController {

    private final MemorandumFileService memorandumFileService;
    private final MemorandumCRUD memorandumCRUD;


    //  조회 ==========================================================================
    //    나의 결재문서 송신함(검색어가 있고 없고의 차이로 보여준다.)
    @GetMapping("/all")
    public String memorandumViewPage(Model model, @PageableDefault(page = 0, size = 15, sort = "memorandumId", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "search", required = false) String search, HttpSession currentSession) {

        PoliceEntity policeEntity = memorandumCRUD.makePoliceEntityWithSessionPoliceId(memorandumCRUD.changeStringPoliceIdLongPoliceId(currentSession));
//        검색어가 없는 경우
        Page<MemorandumDto> memorandumDtoPage = memorandumCRUD.findAllMemoNoSearch(policeEntity, pageable);
//        검색어가 있는 경우
        if (search != null) {
            memorandumDtoPage = memorandumCRUD.findAllMemoWithSearch(search, pageable);
        }

//        각 문서별 결재자 보이기
        List<MemoApprovedMemberDto> memoApprovedMemberDtoList = memorandumCRUD.showApprovingMemberListEachMemo(currentSession, pageable);

//        Pagination 설정
        Long total = memorandumDtoPage.getTotalElements();
        int bockNum = 4;
        int nowPage = memorandumDtoPage.getNumber() + 1;
        int startPage = Math.max(1, memorandumDtoPage.getNumber() - bockNum);
        int endPage = memorandumDtoPage.getTotalPages();

        model.addAttribute("total", total);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        for(MemorandumDto each:memorandumDtoPage){
            System.out.println("This is memo's title :"+each.getMemorandumTitle());
        }


        model.addAttribute("memorandumDtoPage", memorandumDtoPage);
        model.addAttribute("approvedNum", memoApprovedMemberDtoList);
        return "memorandum/MemorandumIndex";
    }

    //    결재 문서 상세 보기
    @GetMapping("/memoDetail/{memorandumId}")
    public String memorandumDetailPage(@PathVariable String memorandumId, Model model) {

        Long memorandumIdLong = Long.valueOf(String.valueOf(memorandumId));

//        결재 문서 상세 내용 가져오기
        MemorandumDto memorandumDto = memorandumCRUD.showSelectedMemo(memorandumIdLong);

//        결재 문서에 해당 하는 파일 보여주기
        List<MemorandumFileDto> memorandumFileDtoList = memorandumFileService.findAllFilesInSelectedMemo(memorandumIdLong);

        //        선택한 결재문서에 등록된 결재선 가져오기
        String policeName = memorandumCRUD.returnApprovedMemberName(memorandumIdLong);
//        등록된 결재선과 로그인한 사람이 같으면, 결재 처리 박스가 보이도록 함.
        String approvingPoliceId = memorandumCRUD.returnApprovingActions(policeName);

        model.addAttribute("fileInDetailMemo", memorandumFileDtoList);
        model.addAttribute("detailMemo", memorandumDto);
        model.addAttribute("policeName", policeName);
        model.addAttribute("approvingPoliceId", approvingPoliceId);

        return "memorandum/MemorandumDetail";


    }
//  조회 ==========================================================================


//  작성 ==========================================================================

    //    결재 문서 작성 페이지 이동
    @GetMapping({"/memoWritePage"})
    public String memorandumWritePage(MemorandumDto memorandumDto, Model model) {
//    결재문서의 결재자 선택을 위해, 전체 경찰의 이름, 부서명 반환
        List<ApprovingMemberAllDto> approvingMemberNameDeptList = memorandumCRUD.showAllApprovingMemberNameDeptSelectedMemo();

//        결재선 지정을 위해, ApprovingMemberAllDto 객체를 전달
        model.addAttribute("approveMember", new ApprovingMemberAllDto());
        model.addAttribute("approveMemberNameDept", approvingMemberNameDeptList);
        model.addAttribute("MemorandumDto", memorandumDto);
        return "memorandum/MemorandumWrite";
    }

    //    결재 문서 작성
    @PostMapping("/write")
    public String memorandumWrite(MemorandumDto memorandumDto, @RequestParam("file") List<MultipartFile> files, HttpSession currentSession, @RequestParam("policeName") String policeName) throws IOException {

//            현재 Police ID를 Session에서 받아온다.
        Long policeIdLong = memorandumCRUD.changeStringPoliceIdLongPoliceId(currentSession);

//        결재 문서 작성
        Long memorandumId = memorandumCRUD.writeMemorandum(memorandumDto, policeIdLong);
//      결재 문서의 결재선 지정
        memorandumCRUD.setApprovingMember(policeName, memorandumId);

//        파일 첨부 -> 작성한 결재 문서를 다시 찾아서, 해당 문서를 파일 테이블에 주입
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile, memorandumDto, memorandumId, policeIdLong);
        }

        return "redirect:/memo/all";
    }

//  작성 ==========================================================================


    //  수정 ==========================================================================
    //    결재 문서 수정페이지 이동
    //    수정할 결재 문서에 모든 데이터를 보여줌
    @GetMapping("/updateMemoPage/{memorandumId}")
    public String memorandumUpdatePage(@PathVariable Long memorandumId, Model model) {

//        수정할 문서의 내용 전체 전달
        MemorandumDto memorandumDto = memorandumCRUD.showSelectedMemo(memorandumId);
        model.addAttribute("MemorandumDto", memorandumDto);

//        수정할 문서에 들어있는 첨부 파일 전달
        List<MemorandumFileDto> memorandumFileDtoList = memorandumFileService.findAllFilesInSelectedMemo(memorandumId);

        model.addAttribute("fileInDetailMemo", memorandumFileDtoList);
        model.addAttribute("memorandumId", memorandumId);

//        결재자 수정을 위해 경찰 Entity를 기준으로 하여, ApprovinMembers를 전체 전달
        List<ApprovingMemberAllDto> approvingMemberNameDeptList = memorandumCRUD.findApprovingMembersByPoliceEntities();

        model.addAttribute("approveMember", new ApprovingMemberAllDto());
        model.addAttribute("approveMemberNameDept", approvingMemberNameDeptList);


        return "memorandum/MemorandumUpdate";
    }

    //    결재 문서 수정하기
    @PostMapping("/updateMemo/{memorandumId}")
    public String memorandumUpdate(@PathVariable Long memorandumId, @RequestParam("file") List<MultipartFile> files, HttpSession currentSession, MemorandumDto memorandumDto, @RequestParam("policeName") String updatedApprovingPoliceName) throws IOException {

//            현재 Police ID를 Session에서 받아온다.
        Long sessionPoliceIdLong = memorandumCRUD.changeStringPoliceIdLongPoliceId(currentSession);

//        수정할 결재 문서 상세 내용을 다시 가져오기
        MemorandumDto memorandumDtoFound = memorandumCRUD.showSelectedMemo(memorandumId);
//        수정할 결재 문서에 제목, 내용 등을 View에서 받은 값으로 다시 넣기
        memorandumDtoFound.setMemorandumTitle(memorandumDto.getMemorandumTitle());
        memorandumDtoFound.setMemorandumContent(memorandumDto.getMemorandumContent());

//        수정할 결재 문서에 해당하는 Approving Member찾고 수정한 결재선으로 다시 넣기
        MemoApprovedMember approvedMemberPolice = memorandumCRUD.findApprovedMemberInSelectedMemo(memorandumId);
        PoliceEntity policeEntity = memorandumCRUD.findPoliceEntityByPoliceName(updatedApprovingPoliceName);
        approvedMemberPolice.setPolice(policeEntity);
        memorandumCRUD.updateSelectedMemoApprovingMember(approvedMemberPolice);

//        수정하기
        memorandumCRUD.updateMemorandum(memorandumDtoFound, sessionPoliceIdLong);

//        파일 첨부 -> 작성한 결재 문서를 다시 찾아서, 해당 문서를 파일 테이블에 주입
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile, memorandumDto, memorandumId, sessionPoliceIdLong);
        }

        return "redirect:/memo/all";
    }


//  수정 ==========================================================================

    //  삭제 ==========================================================================
    //    결재 문서 삭제하기
    @GetMapping("/delete/{memorandumId}")
    public String memorandumSelectDelete(@PathVariable("memorandumId") Long memoId, HttpSession currentSession) {

//        결재 문서 찾아서 삭제
        memorandumCRUD.deleteSelectedMemo(memoId);

//        결재 문서에 해당 하는 파일 삭제
        memorandumFileService.deleteFilesInSelectedMemo(memoId);


        return "redirect:/memo/all";
    }
//  삭제 ==========================================================================

}
