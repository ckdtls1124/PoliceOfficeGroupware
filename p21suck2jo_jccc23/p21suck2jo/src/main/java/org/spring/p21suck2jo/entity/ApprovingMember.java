package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approvingMember")
public class ApprovingMember {

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



}
