package org.spring.p21suck2jo.APIs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/weather")
public class OpenWeatherAPIController {

    @GetMapping("/view")
    public String openWeatherAPIPage(){
        return "/APIs/OpenWeatherAPI";
    }

}
