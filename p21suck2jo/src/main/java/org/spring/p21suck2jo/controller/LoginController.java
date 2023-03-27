package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.PoliceLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PoliceLoginService policeLoginService;

    @GetMapping({"/",""})
    public String basic(){
        return "login/login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @GetMapping("/login")                               //로그인 오류
    public String login(@RequestParam(value = "error" ,required = false ) String error,
                        @RequestParam(value = "exception" ,required = false)String exception,
                        Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "login/login";
    }
    @GetMapping("/idSearch")
    public String idsearch(){
        return "login/idSearch";
    }

    @PostMapping("/idSearch")
    public String policenumber(@RequestParam int policeNumber,
                               Model model){
        PoliceDto policeDto=policeLoginService.policeid(policeNumber);
        model.addAttribute("teamDto",policeDto);
        if(policeDto==null){
            return "login/error";
        }else {
            System.out.println("조회성공");
            return "login/idSearch1";
        }
    }
    @GetMapping("/pwSearch")
    public String pwsearch(){
        return "login/pwSearch";
    }

    @PostMapping("/pwSearch")
    public String pwsearch1(@RequestParam String email,
                            @RequestParam int policeNumber,
                            Model model){

        PoliceDto policeDto=policeLoginService.policepw(email,policeNumber);
        model.addAttribute("teamDto",policeDto);
        if(policeDto==null){
            return "login/error";
        }else {
            System.out.println("조회성공");
            return "login/pwSearch1";
        }
    }
    @PostMapping("/pwSearch1")
    public String pwUpdate(@ModelAttribute PoliceDto policeDto){
        policeLoginService.pwUpdate(policeDto);
        System.out.println("수정 성공!!!");
        return "redirect:/login";
    }
}