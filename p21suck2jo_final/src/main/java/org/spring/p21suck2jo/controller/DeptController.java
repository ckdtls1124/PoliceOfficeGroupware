package org.spring.p21suck2jo.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.DeptService;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;


    //부서 추가 Post form
    @PostMapping("/insert")
    public String deptInsert(DeptDto deptDto){
        deptService.deptInsert(deptDto);
        return "redirect:/dept/list";
    }

    //부서 목록 List View
    @GetMapping("/list")
    public String deptList(Model model){
        model.addAttribute("deptList", deptService.deptList());
        return "/dept/deptList";
    }

    //부서 상세목록 List view
    @GetMapping("/detail/{id}")
    public String deptDetail(@PathVariable Long id, Model model) {
        DeptDto deptDto = deptService.beLongToDept(id);
        List<PoliceDto> dto = deptService.deptDetail(deptDto.getDeptId());
        model.addAttribute("deptList", deptDto);
        model.addAttribute("policeList", dto);
        return "/dept/deptDetail";
    }

    //부서명 수정 View
    @GetMapping("/update/view/{id}")
    public String deptUpdateView(@PathVariable Long id , Model model){
        model.addAttribute("dept",deptService.beLongToDept(id));
        return "/dept/deptUpdateView";
    }

    //부서 수정 Post form
    @PostMapping("/update")
    public String deptUpdate(@ModelAttribute DeptDto deptDto){
        deptService.deptUpdate(deptDto);
        return "redirect:/dept/list";
    }

    //부서 삭제 Post form
    @PostMapping("/delete/{id}")
    public String deptDelete(@PathVariable Long id){
        deptService.deptDelete(id);
        return "redirect:/dept/list";
    }




}
