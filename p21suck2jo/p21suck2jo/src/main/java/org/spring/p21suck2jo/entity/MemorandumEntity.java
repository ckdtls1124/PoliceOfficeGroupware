package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memorandum")
public class MemorandumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memorandum_id")
    private Long memorandumId;


    private String memorandumTitle;

    @Column(columnDefinition = "TEXT")
    private String memorandumContent;


//    승인 여부 확인
    private int approval;



    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

//    승인을 해주는 member의 list를 가진다.
    @OneToMany
    private List<MemberEntity> approvingMember = new ArrayList<>();

}
