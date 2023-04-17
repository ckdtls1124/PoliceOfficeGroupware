package org.spring.openapi1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping({"","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/weather")
    public String weather(){
        return "weather/index";
    }
    @GetMapping("/movie")
    public String movie(){
        return "movie/index";
    }
    @GetMapping("/bus")
    public String bus(){
        return "bus/index";
    }

}
