package org.spring.p21suck2jo.entity;

import lombok.*;
import org.spring.p21suck2jo.dto.BoardDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "board")
public class BoardEntity extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

    @Column(nullable = false)
    @NotBlank(message = "제목을 입력해주세요!")
    private String boardTitle;

    @Column(nullable = false, length = 10000)
    @NotBlank(message = "내용을 입력해주세요!")
    private String boardContent;

    @Column(nullable = false)
    private String boardWriter;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.ALL)
    private List<ReplyEntity> replyList = new ArrayList<>();


    public static BoardEntity toboardEntity(BoardDto boardDto){

        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setViews(boardDto.getViews());
        boardEntity.setPolice(boardDto.getPolice());

        return boardEntity;
    }
    public static BoardEntity toAllboardEntity(BoardDto boardDto){

        BoardEntity boardEntity=new BoardEntity();

        boardEntity.setBoardId(boardDto.getBoardId());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setViews(boardDto.getViews());
        boardEntity.setPolice(boardDto.getPolice());

        return boardEntity;
    }


}
