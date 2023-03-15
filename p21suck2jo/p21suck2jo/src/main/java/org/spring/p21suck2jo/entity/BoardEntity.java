package org.spring.security02.entity;

import lombok.*;

import javax.persistence.*;

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
    private Long id;

    private String boardTitle;
    private String boardContent;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;




}
