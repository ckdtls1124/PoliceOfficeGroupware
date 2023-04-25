package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.PoliceEntity;
import org.spring.groupware.entity.ReplyEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDto {

    public Long replyId;
    public String replyWriter;
    public String replyContent;
    public Long boardId; // BoardEntity의 boardId -> 게시글 조회시
    public LocalDateTime createTime;
    public LocalDateTime updateTime;
    public PoliceEntity police;


    public static ReplyDto toReplyDto(ReplyEntity replyEntity, Long boardId){

        ReplyDto replyDto =new ReplyDto();

        replyDto.setReplyId(replyEntity.getReplyId());
        replyDto.setReplyContent(replyEntity.getReplyContent());
        replyDto.setReplyWriter(replyEntity.getReplyWriter());
        replyDto.setCreateTime(replyEntity.getCreateTime());
        replyDto.setBoardId(boardId);
        replyDto.setPolice(replyEntity.getPolice());

        return replyDto;
    }

}

