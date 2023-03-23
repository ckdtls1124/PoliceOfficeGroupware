package org.spring.p21suck2jo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping({"", "/"})
    public String mainIndex(HttpSession currentPoliceIdSession){
        //            PoliceId를 session에 저장하여, Thymeleaf에서 받을 수 있다.
        currentPoliceIdSession.setAttribute("currentPoliceId", 2);

        return "index";
    }
}
