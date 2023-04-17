package org.spring.openapi1.openapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/open")
public class OpenApi {

    @GetMapping("/bus")
    public String bus(){
        return "api/bus/index";
    }
}
