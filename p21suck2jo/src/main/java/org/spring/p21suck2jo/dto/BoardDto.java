package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {

    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private int views;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private ReplyEntity replyEntity;
    private PoliceEntity police;

//    private MemberEntity memberId;

    public static BoardDto toboardDto(BoardEntity boardEntity){

        BoardDto boardDto=new BoardDto();

        boardDto.setBoardId(boardEntity.getBoardId());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setBoardContent(boardEntity.getBoardContent());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setViews(boardEntity.getViews());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setPolice(boardEntity.getPolice());

        return boardDto;
    }

}

