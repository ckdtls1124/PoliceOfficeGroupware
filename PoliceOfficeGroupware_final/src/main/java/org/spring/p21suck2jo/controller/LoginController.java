package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.BoardDto;
import org.spring.p21suck2jo.dto.EventDto;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PoliceLoginService policeLoginService;
    private final CalenderService calenderService;
    private final EventService eventService;
    private final BoardService boardService;

    private final MemorandumService memorandumService;

    @GetMapping({"/",""})
    public String basic(){
        return "login/login";
    }

    //    현재 로그인한 경찰관에 대한, ID 값을 Session에 넣는다.
    @GetMapping("/index")
    public String index(HttpServletRequest request, HttpSession currentPoliceIdSession, Model model){
        Principal principal = request.getUserPrincipal();
        Long policeId= policeLoginService.findPoliceIdByEmail(principal.getName());
        currentPoliceIdSession.setAttribute("currentPoliceId", policeId.toString());

        //오늘자 기준 스케줄과 현재 세션(policeId)를 가지고 보낸다
        model.addAttribute("works",calenderService.todayWorks(policeId));
        LocalDate today = LocalDate.now(); //현재 날짜를 변수에 담아서
        model.addAttribute("today",today); //모델로 보낸다

        List<EventDto> todayEvent = eventService.todayEvent();
        model.addAttribute("event", todayEvent);

        List<BoardDto> todayBoard=boardService.todayBoard();
        model.addAttribute("board",todayBoard);

        List<MemorandumDto> receivedMemorandumDtoPage = memorandumService.findReceivedAllMemoByLogInPoliceId(policeId);
        model.addAttribute("receivedMemorandumDtoPage", receivedMemorandumDtoPage);

        return "index";
    }


    @GetMapping("/login")                               //로그인 오류
    public String login(@RequestParam(value = "error" ,required = false ) String error,
                        @RequestParam(value = "exception" ,required = false)String exception,
                        Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "login/login";
    }
    @GetMapping("/idSearch")
    public String idsearch(){
        return "login/idSearch";
    }

    @PostMapping("/idSearch")
    public String policenumber(@RequestParam int policeNumber,
                               Model model){
        PoliceDto policeDto=policeLoginService.policeid(policeNumber);
        model.addAttribute("teamDto",policeDto);
        if(policeDto==null){
            return "login/error";
        }else {
            System.out.println("조회성공");
            return "login/idSearch1";
        }
    }
    //smtp
    @GetMapping("/pwSearch")
    public String pwsearchapi(){
        return "login/smtppwSearch";
    }
    //비밀번호찾기 고전방법
//    @GetMapping("/pwSearch")
//    public String pwsearch(){
//        return "login/pwSearch";
//    }
//
//    @PostMapping("/pwSearch")
//    public String pwsearch1(@RequestParam String email,
//                            @RequestParam int policeNumber,
//                            Model model){
//
//        PoliceDto policeDto=policeLoginService.policepw(email,policeNumber);
//        model.addAttribute("teamDto",policeDto);
//        if(policeDto==null){
//            return "login/error";
//        }else {
//            System.out.println("조회성공");
//            return "login/pwSearch1";
//        }
//    }
//    @PostMapping("/pwSearch1")
//    public String pwUpdate(@ModelAttribute PoliceDto policeDto){
//        policeLoginService.pwUpdate(policeDto);
//        System.out.println("수정 성공!!!");
//        return "redirect:/login";
//    }

}