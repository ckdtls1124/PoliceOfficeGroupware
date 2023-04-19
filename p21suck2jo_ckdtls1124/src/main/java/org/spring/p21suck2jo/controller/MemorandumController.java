package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.*;
import org.spring.p21suck2jo.entity.MemoApprovedMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.MemoApprovedMemberRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.MemorandumFileService;
import org.spring.p21suck2jo.service.MemorandumService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemorandumController {

    private final MemorandumFileService memorandumFileService;
    private final MemorandumService memorandumService;


//  조회 ==========================================================================
    //    나의 결재문서 송신함(검색어가 있고 없고의 차이로 보여준다.)
    @GetMapping("/all")
    public String memorandumViewPage(Model model, @PageableDefault(page = 0, size = 15, sort = "memorandumId",
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "search", required = false) String search, HttpSession currentSession) {

        PoliceEntity policeEntity = memorandumService.makePoliceEntityWithSessionPoliceId(memorandumService.changeStringPoliceIdLongPoliceId(currentSession));
        Page<MemorandumDto> memorandumDtoPage;
//        검색어가 없는 경우
        memorandumDtoPage = memorandumService.findAllMemoNoSearch(policeEntity, pageable);

        if(search != null){
//        검색어가 있는 경우
            memorandumDtoPage = memorandumService.findAllMemoWithSearch(search, pageable);
        }
//        각 문서별 결재자 보이기
        List<MemoApprovedMemberDto> memoApprovedMemberDtoList = memorandumService.showApprovingMemberList(currentSession, pageable);

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


        model.addAttribute("memorandumDtoPage", memorandumDtoPage);
        model.addAttribute("approvedNum", memoApprovedMemberDtoList);
        return "memorandum/MemorandumIndex";
    }

    //    결재 문서 상세 보기
    @GetMapping("/memoDetail/{memorandumId}")
    public String memorandumDetailPage(@PathVariable String memorandumId, Model model) {

        Long memorandumIdLong = Long.valueOf(String.valueOf(memorandumId));

//        결재 문서 상세 내용 가져오기
        MemorandumDto memorandumDto = memorandumService.findSelectedMemo(memorandumIdLong);

//        결재 문서에 해당 하는 파일 보여주기
        List<MemorandumFileEntity> memorandumFileEntityList = memorandumFileService.findAllFilesInSelectedMemo(memorandumIdLong);
        List<MemorandumFileDto> memorandumFileDtoList = new ArrayList<>();
        for (MemorandumFileEntity memorandumFileEntity : memorandumFileEntityList) {
            MemorandumFileDto memorandumFileDto = MemorandumFileDto.builder()
                    .memorandumFileId(memorandumFileEntity.getMemorandumFileId())
                    .memorandumFileOriginalName(memorandumFileEntity.getMemorandumFileOriginalName())
                    .build();
            memorandumFileDtoList.add(memorandumFileDto);
        }

//        선택한 결재문서에 등록된 결재선 가져오기
        String policeName = memorandumService.returnApprovedMemberName(memorandumIdLong);
//        등록된 결재선과 로그인한 사람이 같으면, 결재 처리 박스가 보이도록 함.
        String approvingPoliceId = memorandumService.returnApprovingActions(policeName);

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

        List<ApprovingMemberAllDto> approvingMemberNameDeptList = memorandumService.showAllApprovingMemberNameDept();

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
        Long policeIdLong = memorandumService.changeStringPoliceIdLongPoliceId(currentSession);

//        결재 문서 작성
        Long memorandumId = memorandumService.writeMemorandum(memorandumDto, policeIdLong);
//      결재 문서의 결재선 지정
        memorandumService.setApprovingMember(policeName, memorandumId);

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
        MemorandumDto memorandumDto = memorandumService.findSelectedMemo(memorandumId);
        model.addAttribute("MemorandumDto", memorandumDto);

//        수정할 문서에 들어있는 첨부 파일 전달
        List<MemorandumFileEntity> memorandumFileEntityList = memorandumFileService.findAllFilesInSelectedMemo(memorandumId);
        List<MemorandumFileDto> memorandumFileDtoList = new ArrayList<>();
        for (MemorandumFileEntity memorandumFileEntity : memorandumFileEntityList) {
            MemorandumFileDto memorandumFileDto = MemorandumFileDto.builder()
                    .memorandumFileId(memorandumFileEntity.getMemorandumFileId())
                    .memorandumFileOriginalName(memorandumFileEntity.getMemorandumFileOriginalName())
                    .build();
            memorandumFileDtoList.add(memorandumFileDto);
        }

        model.addAttribute("fileInDetailMemo", memorandumFileDtoList);
        model.addAttribute("memorandumId", memorandumId);

//        결재자 수정을 위해 모든 경찰의 정보를 전달
//        PoliceService에 주입하기
        List<PoliceDto> policeDtos = memorandumService.findAllPolice();
        List<ApprovingMemberAllDto> approvingMemberNameDeptList = new ArrayList();

        for (PoliceDto i : policeDtos) {
            ApprovingMemberAllDto approvingMemberNameDept = new ApprovingMemberAllDto();
            approvingMemberNameDept.setDeptName(i.getDept().getDeptName());
            approvingMemberNameDept.setPoliceName(i.getPoliceName());
            approvingMemberNameDeptList.add(approvingMemberNameDept);
        }

        model.addAttribute("approveMember", new ApprovingMemberAllDto());
        model.addAttribute("approveMemberNameDept", approvingMemberNameDeptList);


        return "memorandum/MemorandumUpdate";
    }

    //    결재 문서 수정하기
    @PostMapping("/updateMemo/{memorandumId}")
    public String memorandumUpdate(@PathVariable Long memorandumId, @RequestParam("file") List<MultipartFile> files, HttpSession currentSession, MemorandumDto memorandumDto, @RequestParam("policeName") String updatedApprovingPoliceName) throws IOException {

//            현재 Police ID를 Session에서 받아온다.
        Long sessionPoliceIdLong = memorandumService.changeStringPoliceIdLongPoliceId(currentSession);

//        수정할 결재 문서 상세 내용을 다시 가져오기
        MemorandumDto memorandumDtoFound = memorandumService.findSelectedMemo(memorandumId);
//        수정할 결재 문서에 제목, 내용 등을 View에서 받은 값으로 다시 넣기
        memorandumDtoFound.setMemorandumTitle(memorandumDto.getMemorandumTitle());
        memorandumDtoFound.setMemorandumContent(memorandumDto.getMemorandumContent());

//        수정할 결재 문서에 해당하는 Approving Member찾고 수정한 결재선으로 다시 넣기
        MemoApprovedMember approvedMemberPolice = memorandumService.findApprovedMemberInSelectedMemo(memorandumId);
        PoliceEntity policeEntity = memorandumService.findPoliceEntityByPoliceName(updatedApprovingPoliceName);
        approvedMemberPolice.setPolice(policeEntity);
        memorandumService.updateSelectedMemoApprovingMember(approvedMemberPolice);

//        수정하기
        memorandumService.updateMemorandum(memorandumDtoFound, sessionPoliceIdLong);

//        결재 문서에 해당 하는 파일 보여주기
        List<MemorandumFileEntity> memorandumFileEntityList = memorandumFileService.findAllFilesInSelectedMemo(memorandumId);
        List<MemorandumFileDto> memorandumFileDtoList = new ArrayList<>();
        for (MemorandumFileEntity memorandumFileEntity : memorandumFileEntityList) {
            MemorandumFileDto memorandumFileDto = MemorandumFileDto.builder()
                    .memorandumFileId(memorandumFileEntity.getMemorandumFileId())
                    .memorandumFileOriginalName(memorandumFileEntity.getMemorandumFileOriginalName())
                    .build();
            memorandumFileDtoList.add(memorandumFileDto);
        }
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
        memorandumService.deleteSelectedMemo(memoId);

//        결재 문서에 해당 하는 파일 삭제
        memorandumFileService.deleteFilesInSelectedMemo(memoId);


        return "redirect:/memo/all";
    }
//  삭제 ==========================================================================

}
