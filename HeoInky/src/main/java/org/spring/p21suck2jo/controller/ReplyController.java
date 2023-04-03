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
    public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model,
                             @RequestParam(value = "key",required = false) String key){


        replyService.insertReply(replyDto);

        List<ReplyDto> replyList= replyService.replyList(replyDto.getBoardId());

        model.addAttribute("replyList",replyList);

        return "redirect:/boardDetail/"+ replyDto.getBoardId()+"/"+key;
    }

    @PostMapping("/replyUpdate")
    public String replyUpdate(@ModelAttribute ReplyDto replyDto, Model model,
                              @RequestParam(value = "key",required = false) String key){

        Long updateReply=replyService.updateReply(replyDto);

        model.addAttribute("updateReply",updateReply);

        return "redirect:/boardDetail/"+ replyDto.getBoardId()+"/"+key;
    }

    @GetMapping("/replyDelete")
    public String replyDelete(@RequestParam Long boardId, @RequestParam Long replyId, @RequestParam String key){

        replyService.replyDelete(replyId,boardId);

        return "redirect:/boardDetail/"+boardId+"/"+key;
    }






}
