package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.service.DeptService;
import org.spring.p21suck2jo.service.MemorandumService;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final MemorandumService memorandumService;

    //경찰관 추가 View
    @GetMapping("/insert")
    public String policeAddView(Model model){
        model.addAttribute("police",new PoliceDto());
        model.addAttribute("deptList",deptService.deptList());
        return "/police/officerInsert";
    }

    //경찰관 추가 form Post
    @PostMapping("/insert")
    public String policeAdd(PoliceDto policeDto) {
//        경찰관 가입 시, ApprovingMemberAllEntity에 경찰이름, 부서명 넣기
        memorandumService.addApprovingMemberAllEntity(policeDto.getPoliceName(), policeDto.getDept().getDeptName());
        policeService.policeAdd(policeDto);
        return "redirect:/index";
    }


    @GetMapping("/list")
    public String policeListPage(@PageableDefault(page = 0 , size = 4 , sort = "policeId",
            direction = Sort.Direction.ASC)Pageable pageable, Model model,@RequestParam(value = "search",required = false) String search){

        Page<PoliceDto> dtoPage= policeService.PoliceListPaging(pageable);

        if(search != null){
            dtoPage = policeService.policeListSearch(pageable,search);
        }

        Long total =  dtoPage.getTotalElements();
        int blockNum = 4;
        int nowPage = dtoPage.getNumber()+1;
        int startPage = Math.max(1, dtoPage.getNumber()-blockNum);
        int endPage = dtoPage.getTotalPages();

        model.addAttribute("pageList",dtoPage);
        model.addAttribute("total",total);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "/police/officerList";

    }



    //경찰관 상세조회
    @GetMapping("/list/{id}")
    public String adminPoliceUpdate(@PathVariable("id") Long id,Model model){
        PoliceDto dto = policeService.policeDetail(id);
        model.addAttribute("police",dto);
        model.addAttribute("deptList",deptService.deptList());
        return "/police/adminOfficerUpdate";
    }

    //마이페이지 View
    @GetMapping("/mypage")
    public String myPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        model.addAttribute("police",policeService.policeEmailSearch(email));
        return "/police/officerMypage";

    }

    //내 정보 수정 form Post
    @PostMapping("/mypage/update")
    public String myPageUpdate(@ModelAttribute PoliceDto policeDto){
        policeService.myPageUpdate(policeDto);
        return "redirect:/index";
    }

    //관리자 회원수정 form Post
    @PostMapping("/user/update")
    public String updatePolice(@ModelAttribute PoliceDto policeDto){
        policeService.updatePolice(policeDto);
        return "redirect:/index";
    }

    //관리자 경찰관 정보 삭제
    @PostMapping("/list/delete/{id}")
    public String adminPoliceDelete(@PathVariable Long id){

        policeService.policeDelete(id);
        return "redirect:/police/list";
    }





}
