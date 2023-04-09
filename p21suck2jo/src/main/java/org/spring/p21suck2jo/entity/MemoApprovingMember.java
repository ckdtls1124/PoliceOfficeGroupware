package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approvingMember")
public class MemoApprovingMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approvingMember_id")
    private Long approvingMemberId;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

    @ManyToOne
    @JoinColumn(name = "memorandum_id")
    private MemorandumEntity memorandum;

//    특정 결재 문서에 대해, 지정된 결재자가 결재 상태 변경
//    결재 승인 : 2, 결재 취소(반려) : 1, 대기(결재 문서 상신 시) : 0
    private int approved;



}
