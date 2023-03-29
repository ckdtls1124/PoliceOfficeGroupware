package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.config.UserDetailSecurity;
import org.spring.p21suck2jo.dto.BoardDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.service.BoardService;
import org.spring.p21suck2jo.service.PoliceService;
import org.spring.p21suck2jo.service.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final PoliceService policeService;

    @GetMapping("/boardWrite")
    public String boardWriteView(@AuthenticationPrincipal UserDetails user, Model model ){


        PoliceDto policeDto= policeService.findByPoliceName(user.getUsername());

        if(policeDto!=null){
            model.addAttribute("policeName",policeDto.getPoliceName());
        }

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
    public String boardDetail(@PathVariable("boardId") Long boardId,@AuthenticationPrincipal UserDetails user, Model model, HttpServletRequest request
                            , HttpServletResponse response){
        // 해당 게시판 번호를 받아 리뷰 상세페이지로 넘겨줌
        BoardDto boardDtos= boardService.boardDetail(boardId);
        PoliceDto police=policeService.findByPoliceId(user.getUsername());

        Cookie[] cookies= request.getCookies();

        // 비교하기 위해 새로운 쿠키
        Cookie oldCookie=null;

        //쿠키가 있을 경우
        if(cookies!=null && cookies.length>0){
            for(int i=0;i< cookies.length;i++){
                // Cookie의 name이 cookie + boardId와 일치하는 쿠키를 oldCookie에 넣어줌
                if(cookies[i].getName().equals("cookie"+police.getPoliceId())){
                    oldCookie=cookies[i];
                }
            }
        }
        if(boardDtos!=null){
            model.addAttribute("boardDtos",boardDtos);

            // 만일 oldCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리
            if (oldCookie==null) {

                // 쿠키 생성(이름, 값)
                Cookie newCookie = new Cookie("cookie" + police.getPoliceId(), "|" + police.getPoliceId() + "|");

                //쿠키 추가
                response.addCookie(newCookie);

                //쿠기를 추가 시키고 조회수 증가시킴
                int rs = boardService.upViews(boardId);

                if (rs > 0) {
                    System.out.println("View up");
                } else {
                    System.out.println("View up error");
                }
                // oldCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음
            }else{

                String value= oldCookie.getValue();

                System.out.println("cookie value : "+value);
            }

            List<ReplyDto> replyList=replyService.replyList(boardId);
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

        return "redirect:/boardDetail/"+boardDto.getBoardId();
    }

    @GetMapping("/boardDelete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId){

        boardService.boardDelete(boardId);

        return "redirect:/board";
    }
}
