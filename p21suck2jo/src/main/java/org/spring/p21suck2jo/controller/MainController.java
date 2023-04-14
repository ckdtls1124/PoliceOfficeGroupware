package org.spring.p21suck2jo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/my", produces = "application/json")
public class MainController {

  @GetMapping("")
  public String index(){
    return "index";
  }

  @GetMapping("schedule")
  public String schedule(){


    return "/calendar/myCalender";
  }

  @GetMapping("schedule/list")
  public String schedule2(){


    return "/calendar/AllCalender";
  }


}
