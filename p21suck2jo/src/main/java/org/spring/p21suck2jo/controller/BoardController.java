package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.BoardDto;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.service.BoardService;
import org.spring.p21suck2jo.service.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("/boardWrite")
    public String boardWriteView(Model model){

        model.addAttribute("boardDto", new BoardDto());
        return "/board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(BoardDto boardDto, Model model){

        if(boardDto==null){
            return "/board/boardWrite";
        }
        boardService.boardWrite(boardDto);

        return "redirect:/board";
    }

    @GetMapping("/board")
    public String boardList(Model model, @PageableDefault(page=0,size=15,sort="boardId",
            direction = Sort.Direction.DESC)Pageable pageable,
                            @RequestParam(value="search",required = false)String search){

        if(search==null){
            Page<BoardDto> boardDtos=boardService.boarListPaging(pageable);

            Long total=boardDtos.getTotalElements();
            int bockNum=4;
            int nowPage= boardDtos.getNumber()+1 ;
            int startPage=Math.max(1,boardDtos.getNumber()-bockNum);
            int endPage=boardDtos.getTotalPages();

            model.addAttribute("boardDtos",boardDtos);
            model.addAttribute("total",total);
            model.addAttribute("nowPage",nowPage);
            model.addAttribute("startPage",startPage);
            model.addAttribute("endPage",endPage);
        }else{
            Page<BoardDto> boardDtos=boardService.boarListSearchPaging(search,pageable);

            Long total=boardDtos.getTotalElements();
            int bockNum=4;
            int nowPage= boardDtos.getNumber()+1 ;
            int startPage=Math.max(1,boardDtos.getNumber()-bockNum);
            int endPage=boardDtos.getTotalPages();

            model.addAttribute("boardDtos",boardDtos);
            model.addAttribute("total",total);
            model.addAttribute("nowPage",nowPage);
            model.addAttribute("startPage",startPage);
            model.addAttribute("endPage",endPage);
        }
        return "/board/board";
    }

    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model ){

        BoardDto boardDtos= boardService.boardDetail(boardId);
        boardService.upViews(boardId);

        if(boardDtos!=null){
            model.addAttribute("boardDtos",boardDtos);
            System.out.println("boardDto ="+boardDtos);

            List<ReplyDto> replyList=replyService.replyList(boardId);
            System.out.println("list = "+replyList);
            model.addAttribute("replyList",replyList);
            return "/board/boardDetail";
        }else{
            return null;
        }
    }

    @GetMapping("/boardUpdate/{boardId}")
    public String boardUpdate(@PathVariable("boardId") Long boardId, Model model){

        BoardDto boardDto=boardService.boardUpdate(boardId);

        model.addAttribute("boardDto",boardDto);

        return "board/boardUpdate";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdateOk(@ModelAttribute BoardDto boardDto){

        boardService.boardUpdateOk(boardDto);

        return "redirect:/board";
    }

    @GetMapping("/boardDelete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId){

        boardService.boardDelete(boardId);

        return "redirect:/board";
    }
}
