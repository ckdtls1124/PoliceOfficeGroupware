package org.spring.p21suck2jo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/my", produces = "application/json")
public class CalenderController {

  @GetMapping("calender")
  public String index(){
    return "calender";
  }

  @GetMapping("schedule")
  public String schedule(){
    return "myCalender";
  }


}
