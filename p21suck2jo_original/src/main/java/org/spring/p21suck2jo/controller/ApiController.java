package org.spring.p21suck2jo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping("/bus")
    public String bus(){
        return "api/bus";
    }
}
