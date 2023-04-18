package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.CalenderDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.CalenderService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
  public List<CalenderDto> setCalendar(@ModelAttribute CalenderDto calenderDto, HttpSession currentSession){

//      session의 getAttribute Object를 Long으로 변환
//        Long으로 변환된 Session의 경찰 아이디는 결재문서 테이블, 결재문서 파일 테이블에 주입된다.
        Long sessionPoliceIdLong = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));


    calenderService.setCalendar(calenderDto, sessionPoliceIdLong);

    return calenderService.eventListAll();
  }

  @GetMapping("/calendar/my")
  @ResponseBody
  public List<CalenderDto> getCalendar(HttpSession currentSession){
    Long sessionPoliceIdLong = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));


    return calenderService.MyEventListAll(sessionPoliceIdLong);
  }

  @GetMapping("/calendar")
  @ResponseBody
  public List<CalenderDto> getCalendar(){
    return calenderService.eventListAll();
  }




}
