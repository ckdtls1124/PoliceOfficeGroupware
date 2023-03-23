package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.dto.MemorandumFileDto;
import org.spring.p21suck2jo.entity.BaseEntity;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.repository.MemorandumFileRepository;
import org.spring.p21suck2jo.service.MemorandumFileService;
import org.spring.p21suck2jo.service.MemorandumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemorandumController {

    private final MemorandumFileService memorandumFileService;
    private final MemorandumService memorandumService;
    private final MemorandumFileRepository memorandumFileRepository;

    //    나의 결재함
    @GetMapping("/{policeId}")
    public String memorandumPage(Model model, @PathVariable("policeId") Long policeId) {
        try {
//            나의 결재 서류 전체 보여주기
            List<MemorandumEntity> memorandumEntityList = memorandumService.findAllMemo(policeId);
            model.addAttribute("myMemoList", memorandumEntityList);


//            List<MemorandumFileEntity> memorandumFileEntities = memorandumFileService.findAllFiles();
//            model.addAttribute("files", memorandumFileEntities);
        } catch (Exception e) {
            System.out.println("No files found");
        }

        return "MemorandumIndex";
    }


    //    결재 문서 작성 페이지 이동
    @GetMapping("/memoWritePage")
    public String MemoWritePage(MemorandumDto memorandumDto, Model model) {

//        결재문서를 작성하면, 승인 여부는 0으로 Default한다.
        memorandumDto.setApproval(0);

//        결재문서 DTO를 보내서 유효성 처리 가능
        model.addAttribute("MemorandumDto", memorandumDto);
        return "MemorandumWrite";
    }


    //    결재 문서 작성
    @PostMapping("/write")
    public String writeMemorandum(MemorandumDto memorandumDto, @RequestParam("file") List<MultipartFile> files, HttpSession currentSession) throws IOException {

//        session의 getAttribute Object를 Int으로 변환
        Integer sessionPoliceId = 0;
        Object storedValue = currentSession.getAttribute("currentPoliceId");
        if (storedValue instanceof Integer) { // ClassCastException 방지
            sessionPoliceId = (Integer) storedValue;
        }

//      session의 getAttribute Object를 Long으로 변환
//        Long으로 변환된 Session의 경찰 아이디는 결재문서 테이블, 결재문서 파일 테이블에 주입된다.
        Long sessionPoliceIdLong = Long.valueOf(Integer.valueOf(sessionPoliceId));

//        결재 문서 작성
//        현재 Session에 있는 경찰 아이디를 Long 타입으로 변환해서 결재문서에 넣는다.
        Long memorandumId = memorandumService.writeMemorandum(memorandumDto, sessionPoliceIdLong);


//        파일 첨부 -> 작성한 결재 문서를 다시 찾아서, 해당 문서를 파일 테이블에 주입
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile, memorandumDto, memorandumId, sessionPoliceIdLong);
        }


        return "redirect:/memo/" + sessionPoliceId;
    }




//    결재 문서 수정페이지 이동
//    수정할 결재 문서 내의 모든 데이터를 이동
    @GetMapping("/updateMemoPage/{memorandumId}")
    public String updateMemoPage(@PathVariable Long memorandumId, Model model){

        MemorandumEntity memorandumEntity= memorandumService.findSelectedMemo(memorandumId);
        MemorandumDto memorandumDto = MemorandumDto.builder()
                .memorandumTitle(memorandumEntity.getMemorandumTitle())
                .memorandumContent(memorandumEntity.getMemorandumContent())
                .build();

        model.addAttribute("MemorandumDto", memorandumDto);
        return "MemorandumWrite";
    }





    //    결재 문서 상세 보기
    @GetMapping("/memoDetail/{memorandumId}")
    public String detailMemoPage(@PathVariable String memorandumId, Model model) {

        Long memorandumIdLong = Long.valueOf(String.valueOf(memorandumId));

//        결재 문서 상세 내용 보여주기
        MemorandumEntity memorandumEntity = memorandumService.findSelectedMemo(memorandumIdLong);
        MemorandumDto memorandumDto = MemorandumDto.builder()
                .memorandumId(memorandumEntity.getMemorandumId())
                .memorandumTitle(memorandumEntity.getMemorandumTitle())
                .memorandumContent(memorandumEntity.getMemorandumContent())
                .approval(memorandumEntity.getApproval())
                .build();

        model.addAttribute("detailMemo", memorandumDto);

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

        model.addAttribute("fileInDetailMemo", memorandumFileDtoList);


        return "MemorandumDetail";


    }




//    결재 문서 삭제하기
    @GetMapping("/delete/{memorandumId}")
    public String deleteSelectedMemo(@PathVariable("memorandumId") Long memoId, HttpSession currentSession) {

//        결재 문서 찾아서 삭제
        memorandumService.deleteSelectedMemo(memoId);

//        결재 문서에 해당 하는 파일 삭제
        memorandumFileService.deleteFilesInSelectedMemo(memoId);


        //        session의 getAttribute Object를 Int으로 변환
        Integer sessionPoliceId = 0;
        Object storedValue = currentSession.getAttribute("currentPoliceId");
        if (storedValue instanceof Integer) { // ClassCastException 방지
            sessionPoliceId = (Integer) storedValue;
        }

        return "redirect:/memo/" + sessionPoliceId;
    }


}
