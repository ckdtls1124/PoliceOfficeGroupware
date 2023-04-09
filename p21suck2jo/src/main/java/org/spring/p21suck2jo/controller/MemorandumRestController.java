package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemorandumRestController {

//  수정 ==========================================================================
    //    결재자가 결재 승인(반려) 실행
    @PostMapping("/approve")
    public ResponseEntity<String> approveMemo(@RequestParam("approveNum") String index) {
        System.out.println("!!!!!This is retrieved data from AJAX!!!!!!!!!!!!! :" + index);

        return new ResponseEntity<>(index, HttpStatus.OK);
    }
//  수정 ==========================================================================
}
