package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.CalenderDto;
import org.spring.p21suck2jo.service.CalenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = "application/json")
@RequiredArgsConstructor
public class ApiController {

  private final CalenderService calenderService;

  @GetMapping("/events")
//  @ResponseBody
  public List<CalenderDto> eventsCalender(){

    List<CalenderDto>  calenderDtos= calenderService.eventListAll();

    return  calenderDtos;
  }

  //일정추가
  @PostMapping("/calendar")
  public List<CalenderDto> setCalendar(@ModelAttribute CalenderDto calenderDto){
    calenderService.setCalendar(calenderDto);

    return calenderService.eventListAll();
  }

  @GetMapping("/calendar")
  @ResponseBody
  public List<CalenderDto> getCalendar(){
    return calenderService.eventListAll();
  }
















}
