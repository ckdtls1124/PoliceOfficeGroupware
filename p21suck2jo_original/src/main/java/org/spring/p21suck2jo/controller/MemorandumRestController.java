package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.service.MemorandumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemorandumRestController {

    private final MemorandumService memorandumService;

//  수정 ==========================================================================
    //    결재자가 결재 승인(반려) 실행
    @PostMapping("/approve")
    public ResponseEntity<String> approveMemo(@RequestParam("approveNum") String index, HttpSession currentSession, @RequestParam("currentMemoId") String memorandumId) {


        Long policeIdLong = memorandumService.changeStringPoliceIdLongPoliceId(currentSession);


        memorandumService.updateApprovedInMemoApprovingMember(index, policeIdLong, memorandumId);


        return new ResponseEntity<>(index, HttpStatus.OK);
    }
//  수정 ==========================================================================
}
