package org.spring.p21suck2jo.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.DeptService;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;
    private final PoliceService policeService;

    @GetMapping("/insert")
    public String deptInsertVIew(Model model){
        model.addAttribute("dept",new DeptDto());
        return "";
    }
    @PostMapping("insert")
    public String deptInsert(DeptDto deptDto){
        deptService.deptInsert(deptDto);
        return "/dept/list";
    }




    @GetMapping("/list")
    public String deptList(Model model){

        model.addAttribute("deptList", deptService.deptList());

        return "/dept/deptList";
    }

    @GetMapping("/detail/{id}")
    public String deptDetail(@PathVariable Long id, Model model){
        DeptDto deptDto = deptService.deptId(id);
//        PoliceDto dto =policeService.policeDetail(deptDto.getDeptId());
        List<PoliceDto> dto = deptService.list2(deptDto.getDeptId());
        model.addAttribute("deptList",deptDto);
        model.addAttribute("policeList",dto);
        return "/dept/deptDetail";
    }
}
