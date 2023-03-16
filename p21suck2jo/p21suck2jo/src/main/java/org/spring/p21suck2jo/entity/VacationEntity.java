package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacation")
public class VacationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long vacationId;


    private String vacationName;
    private String vacationNumber;


//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private MemberEntity member;
}
