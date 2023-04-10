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

    @GetMapping("/boardDetail/{boardId}/{key}")
    public String boardDetail(@PathVariable("boardId") Long boardId,@AuthenticationPrincipal UserDetails user,
                              @PathVariable(value = "key", required = false) String key,
                              Model model){

        // key값이 true이거나 null이 아닌경우 조회수 카운팅 X
        if(key.equals("true") && key!=null) {

            boardService.upViews2(boardId);
        // key값이 true가 아니거나 null인 경우 조회수 카운팅 O
        }else{
            boardService.upViews(boardId);
        }
        // 해당 게시판 번호를 받아 리뷰 상세페이지로 넘겨줌
        BoardDto boardDtos= boardService.boardDetail(boardId);

        PoliceDto policeName= policeService.findByPoliceIdAndName(user.getUsername());

        model.addAttribute("policeReplyName",policeName.getPoliceName());
        model.addAttribute("boardDtos",boardDtos);

//        Cookie[] cookies= request.getCookies();
//
//        // 비교하기 위해 새로운 쿠키
//        Cookie oldCookie=null;
//
//        //cookies가 null이 아니면 cookie의 이름이 postView인지 확인하고, 맞으면 oldCookie에 이 cookie를 대입
//        if(cookies!=null){
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("postView")) {
//                    oldCookie = cookie;
//                }
//            }
//        }
        if(boardDtos!=null){
            model.addAttribute("boardDtos",boardDtos);
//
//            // 만일 oldCookie가 null이 아니고 oldCookie값에 id값이 없을 때 (있다면 이미 조회한 게시물로 조회수가 올라가지 않음) 조회수 올리는 메소드 호출
//            if (oldCookie!=null) {
//
//                if(!oldCookie.getValue().contains("["+ boardId.toString() +"]")){
//                    oldCookie.setValue(oldCookie.getValue() + "_[" + boardId + "]");
//                    oldCookie.setMaxAge(-1);//브라우저 닫으면 쿠키 삭제 닫기전까진 살아있음
//                    response.addCookie(oldCookie);
//                    //쿠기를 추가 시키고 조회수 증가시킴
//                    boardService.upViews(boardId);
//                }
//                // oldCookie가 null일 경우 postView라는 이름으로 쿠키를 만들고 조회수 올리는 메소드 호출
//            }else{
//                Cookie newCookie = new Cookie("postView", "[" + boardId + "]");
//                newCookie.setMaxAge(-1);
//                response.addCookie(newCookie);
//
//                boardService.upViews(boardId);
//            }
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
    public String boardUpdateOk(@ModelAttribute BoardDto boardDto,
                                @RequestParam(value = "key",required = false) String key){

        boardService.boardUpdateOk(boardDto);

        return "redirect:/boardDetail/"+boardDto.getBoardId()+"/"+key;
    }

    @GetMapping("/boardDelete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId){

        boardService.boardDelete(boardId);

        return "redirect:/board";
    }
}
