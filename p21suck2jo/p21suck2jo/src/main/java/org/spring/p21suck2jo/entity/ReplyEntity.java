package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.p21suck2jo.dto.ReplyDto;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "boardReply")
public class ReplyEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="reply_id")
    public Long replyId;

    @Column(nullable = false)
    public String replyWriter;

    @Column(nullable = false,length = 500)
    public String replyContent ;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity policeEntity;

}
=======
/*    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity policeEntity;*/

    public static ReplyEntity toReplyEntity(ReplyDto replyDto, BoardEntity boardEntity){

        ReplyEntity replyEntity =new ReplyEntity();

        replyEntity.setReplyContent(replyDto.getReplyContent());
        replyEntity.setReplyWriter(replyDto.getReplyWriter());
        replyEntity.setBoardEntity(boardEntity);

        return replyEntity;
    }
    public static ReplyEntity toGetReplyEntity(ReplyDto replyDto){

        ReplyEntity replyEntity =new ReplyEntity();

        replyEntity.setReplyContent(replyDto.getReplyContent());
        replyEntity.setReplyWriter(replyDto.getReplyWriter());

        return replyEntity;
    }



}
>>>>>>> a018ced5bc69c03acd702d843658d20688f93d00
