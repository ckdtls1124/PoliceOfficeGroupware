package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDto {

    public Long replyId;
    public String replyWriter;
    public String replyContent;
    public Long boardId;
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

