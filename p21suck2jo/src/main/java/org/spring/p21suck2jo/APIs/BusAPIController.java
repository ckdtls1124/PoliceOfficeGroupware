package org.spring.p21suck2jo.APIs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bus")
public class BusAPIController {

//    JavaScript를 이용
    @GetMapping("/")
    public String busPage(){

        return "/APIs/BusAPIOnlyJavascript";
    }

//    Java를 이용하는 페이지로 이동
    @GetMapping("/busJava")
    public String busPageJava(){
        return "/APIs/BusAPIWithJava";
    }



}
