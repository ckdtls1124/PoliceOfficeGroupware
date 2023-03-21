package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memorandum")
@Builder
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

    @OneToMany(mappedBy = "memorandumEntity",cascade = CascadeType.ALL)
    List<MemorandumFileEntity> memorandumFileList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

    //    승인을 해주는 member의 list를 가진다.
    @OneToMany(mappedBy = "memorandum",cascade = CascadeType.ALL)
    List<ApprovingMember> approvingMemberList = new ArrayList<>();


}