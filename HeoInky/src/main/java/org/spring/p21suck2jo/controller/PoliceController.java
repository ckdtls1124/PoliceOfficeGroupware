package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.service.DeptService;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/police")
@RequiredArgsConstructor
public class PoliceController {

    private final PoliceService policeService;
    private final DeptService deptService;

    @GetMapping("/insert")
    public String policeAddView(Model model){
        model.addAttribute("police",new PoliceDto());
        model.addAttribute("deptList",deptService.deptList());
        return "/police/officerInsert";
    }

    @PostMapping("/insert")
    public String policeAdd(@Valid PoliceDto policeDto){
        policeService.policeAdd(policeDto);
        return "redirect:/index";
    }

    @GetMapping("/list")
    public String policeList(Model model){
       model.addAttribute("policeList",policeService.policeList());
        return "/police/officerList";
    }

    @GetMapping("/list/{id}")
    public String adminPoliceUpdate(@PathVariable("id") Long id,Model model){
        PoliceDto dto = policeService.policeDetail(id);
        model.addAttribute("police",dto);
        model.addAttribute("deptList",deptService.deptList());
        return "/police/adminOfficerUpdate";
    }

    //회원수정(mypage)
    @PostMapping("/update")
    public String policeUpdate(@ModelAttribute PoliceDto policeDto){

        policeService.policeUpdate(policeDto);
        return "redirect:/index";
    }



    @GetMapping("/mypage")
    public String policeList2(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        model.addAttribute("police",policeService.policeEmailSearch(email));

        return "/police/officerMypage";

    }


    @PostMapping("/list/delete/{id}")
    public String adminPoliceDelete(@PathVariable Long id){

        policeService.policeDelete(id);
        return "redirect:/police/list";
    }



}
