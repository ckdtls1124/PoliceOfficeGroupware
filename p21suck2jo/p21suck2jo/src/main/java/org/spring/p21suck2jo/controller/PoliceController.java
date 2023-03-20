package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
<<<<<<< HEAD
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

=======
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
@Controller
@RequestMapping("/police")
@RequiredArgsConstructor
public class PoliceController {

    private final PoliceService policeService;

    @GetMapping("/insert")
<<<<<<< HEAD
    public String policeAddView(Model model){
        model.addAttribute("police",new PoliceDto());
        return "/police/officerInsert";
    }

    @PostMapping("/insert")
    public String policeAdd(@Valid PoliceDto policeDto){
        policeService.policeAdd(policeDto);
        return "index";
    }

    @GetMapping("/list")
    public String policeList(Model model){
       model.addAttribute("policeList",policeService.policeList());
        return "/police/officerList";
    }
    @GetMapping("/list/{id}")
    public String adminPoliceUpdate(@PathVariable Long id,Model model){
        PoliceDto dto = policeService.policeDetail(id);

        model.addAttribute("police",dto);
        return "/police/adminOfficerUpdate";
    }
//    @PostMapping("/update/{id}")
//    public String policeUpdate(@PathVariable Long id){
//        policeService.policeUpdate(id);
//
//        return "redirect:/police/list";
//    }

    @PostMapping("/update/{id}")
    public String policeUpdate(@ModelAttribute PoliceDto policeDto){

        policeService.policeUpdate(policeDto);

        return "redirect:/police/list";
    }

    @GetMapping("/mypage/{id}")
    public String policeList(@PathVariable Long id,Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //security 되면 위 방법으로
        model.addAttribute("police",policeService.policeDetail(id));
        return "/police/officerMypage";
    }


    @PostMapping("/list/delete/{id}")
    public String adminPoliceDelete(@PathVariable Long id){

        policeService.policeDelete(id);
        return "redirect:/police/list";
    }
=======
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

>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
}
