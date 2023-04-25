package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 작성 및 목록
    @PostMapping("/replyWrite")
    public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model,
                             @RequestParam(value = "key", required = false) String key) {

        // 댓글 작성
        replyService.replyWrite(replyDto);
//      System.out.println("id >>>"+replyDto.getBoardId());

        // 댓글 목록 -> 게시글 id(댓글을 단 게시글의 id)의 리스트만 get
        // 댓글을 작성하고 -> 게시글이 존재하는지 확인 -> DTO-> Entity -> 저장
        // 상세목록페이지에 글번호와 댓글리스트를 가지고 같이 보낸다.
        List<ReplyDto> replyList = replyService.replyList(replyDto.getBoardId());
        model.addAttribute("replyList", replyList);

        //게시글 id -> replyDto.getBoardId()/+ 조회수 key
        return "redirect:/boardDetail/" + replyDto.getBoardId() + "/" + key;
    }

    // 댓글 수정
    @PostMapping("/replyUpdate")
    public String replyUpdate(@ModelAttribute ReplyDto replyDto, Model model,
                              @RequestParam(value = "key", required = false) String key) {
        Long updateReply = replyService.replyUpdate(replyDto);

        model.addAttribute("updateReply", updateReply);

        return "redirect:/boardDetail/" + replyDto.getBoardId() + "/" + key;
    }

    // 댓글 삭제
    @GetMapping("/replyDelete")
    public String replyDelete(@RequestParam Long boardId, @RequestParam Long replyId,
                              @RequestParam(value = "key", required = false) String key) {

        // replyId와 boardId를 받아서 테이블에 존재하는 해당 컬럼만 삭제 한다.
        replyService.replyDelete(replyId, boardId);

        return "redirect:/boardDetail/" + boardId + "/" + key;
    }


}
