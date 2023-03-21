package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.service.MemorandumFileService;
import org.spring.p21suck2jo.service.MemorandumService;
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
    private final MemorandumService memorandumService;

    //    나의 결재함
    @GetMapping("/{policeId}")
    public String memorandumPage(Model model, @PathVariable("policeId") Long policeId, HttpSession currentPoliceIdSession) {
        try{
//            나의 결재 서류 전체 보여주기
            List<MemorandumEntity> memorandumEntityList=memorandumService.findAllMemo(policeId);
            model.addAttribute("myMemoList", memorandumEntityList);


//            List<MemorandumFileEntity> memorandumFileEntities = memorandumFileService.findAllFiles();
//            model.addAttribute("files", memorandumFileEntities);
        } catch (Exception e){
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
    public String writeMemorandum(MemorandumDto memorandumDto, @RequestParam("file") List<MultipartFile> files, @RequestParam("policeId") String policeId, HttpSession currentSession) throws IOException {

        System.out.println("This is policeId :"+policeId);

        Long policeIdLong = Long.parseLong(policeId);
//        결재 문서 작성
        Long memorandumId=memorandumService.writeMemorandum(memorandumDto, policeIdLong);



//        파일 첨부 -> 작성한 결재 문서를 다시 찾아서, 해당 문서를 파일 테이블에 주입
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile, memorandumDto, memorandumId);
        }

//        session의 getAttribute Object를 Int으로 변환
        Integer value = 0;
        Object storedValue = currentSession.getAttribute("currentPoliceId");
        if (storedValue instanceof Integer) { // ClassCastException 방지
            value = (Integer) storedValue;
        }



        return "redirect:/memo/"+value;
    }


//    결재 문서 상세 보기
    @GetMapping("/memo/memoDetail/{memorandumId}")
    public String detailMemoPage(@PathVariable Long memoId, Model model){

//        결재 문서 상세 내용 보여주기
        MemorandumEntity memorandumEntity=memorandumService.findSelectedMemo(memoId);
        model.addAttribute("detailMemo", memorandumEntity);

//        결재 문서에 해당 하는 파일 보여주기
        List<MemorandumFileEntity> memorandumFileEntityList= memorandumFileService.findAllFilesInSelectedMemo(memoId);
        model.addAttribute("fileInDetailMemo", memorandumFileEntityList);


        return "MemorandumDetail";
    }

//    결재 문서 삭제하기
    @GetMapping("/delete/{memorandumId}")
    public void deleteSelectedMemo(@PathVariable("memorandumId") Long memoId){

//        결재 문서 찾아서 삭제
        memorandumService.deleteSelectedMemo(memoId);

//        결재 문서에 해당 하는 파일 삭제
        memorandumFileService.deleteFilesInSelectedMemo(memoId);

    }



}
