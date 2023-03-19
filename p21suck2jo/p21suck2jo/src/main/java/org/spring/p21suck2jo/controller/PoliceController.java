package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/police")
@RequiredArgsConstructor
public class PoliceController {

    private final PoliceService policeService;

    @GetMapping("/insert")
    public String InsertView(Model model){
        model.addAttribute("member", new PoliceDto());
        return "/police/officerInsert";

    }

//    @PostMapping("/insert")
//    public String policeInsert(@Valid PoliceDto policeDto){
//         policeService.policeAdd(policeDto);
//         return "main";
//         //버튼에 이벤트 넣어주기
//    }

}
