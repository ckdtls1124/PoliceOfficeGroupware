package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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




}
