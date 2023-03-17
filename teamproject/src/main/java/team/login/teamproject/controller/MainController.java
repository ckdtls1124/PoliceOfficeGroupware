package team.login.teamproject.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team.login.teamproject.dto.WebDto;
import team.login.teamproject.service.WebService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final WebService webService;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("webDto",new WebDto());
        return "/join";
    }
    @PostMapping("/join")
    public String joinPost(@Valid WebDto webDto, BindingResult rs){
        if(rs.hasErrors()){
            return "/join";
        }
        webService.inser(webDto);
        System.out.println("회원가입 성공");
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
