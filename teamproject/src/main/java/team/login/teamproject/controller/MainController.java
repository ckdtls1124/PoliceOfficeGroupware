package team.login.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import team.login.teamproject.dto.TeamDto;
import team.login.teamproject.service.WebService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final WebService webService;

    @GetMapping("/join")
    public String join(Model model) {
//        model.addAttribute("teamDto", new TeamDto());
        return "join";
    }

    @PostMapping("/join")
    public String joinPost(@Valid TeamDto teamDto) {

        webService.inser(teamDto);
        System.out.println("회원가입 성공");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/idSearch")
    public String idsearch(){
        return "idSearch";
    }

    @PostMapping("/idSearch")
    public String policenumber(@RequestParam int policeNumber,
                               Model model){
        TeamDto teamDto=webService.policeid(policeNumber);
        model.addAttribute("teamDto",teamDto);
        if(teamDto==null){
            return "login";
        }else {
        System.out.println("조회성공");
        return "idSearch1";
        }
    }
    @GetMapping("/pwSearch")
    public String pwsearch(){
        return "pwSearch";
    }
    @PostMapping("/pwSearch")
    public String pwsearch1(@RequestParam String email,
                            @RequestParam int policeNumber,
                            Model model){

        TeamDto teamDto=webService.policepw(email,policeNumber);
        model.addAttribute("teamDto",teamDto);
        return "pwSearch1";

    }
    @PostMapping("/pwSearch1")
    public String pwUpdate(@ModelAttribute TeamDto teamDto){
        webService.pwUpdate(teamDto);
        System.out.println("수정 성공");
        return "redirect:/login";
    }
}

