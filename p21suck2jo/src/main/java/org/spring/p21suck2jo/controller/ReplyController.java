package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replyWrite")
    public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model){

        Long rs= replyService.insertReply(replyDto);

        List<ReplyDto> replyList= replyService.replyList(replyDto.getBoardId());

        model.addAttribute("replyList",replyList);

        return "redirect:/boardDetail/"+ replyDto.getBoardId();
    }

}
