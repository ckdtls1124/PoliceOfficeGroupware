package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.BoardDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.service.PoliceService;
import org.spring.p21suck2jo.service.ReplyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final PoliceService policeService;

    @PostMapping("/replyWrite")
    public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model,@AuthenticationPrincipal UserDetails user){

        replyService.insertReply(replyDto);
        PoliceDto police= policeService.findByPoliceName(user.getUsername());


        if(police!=null){
            model.addAttribute("police",police);
        }

        List<ReplyDto> replyList= replyService.replyList(replyDto.getBoardId());

        model.addAttribute("replyList",replyList);

        return "redirect:/boardDetail/"+ replyDto.getBoardId();
    }

    @PostMapping("/replyUpdate")
    public String replyUpdate(@ModelAttribute ReplyDto replyDto, Model model){

        Long updateReply=replyService.updateReply(replyDto);

        model.addAttribute("updateReply",updateReply);

        return "redirect:/boardDetail/"+ replyDto.getBoardId();
    }

    @GetMapping("/replyDelete")
    public String replyDelete(@RequestParam Long boardId, @RequestParam long replyId, Model model){

        replyService.replyDelete(replyId,boardId);

        return "redirect:/boardDetail/"+boardId;
    }






}
