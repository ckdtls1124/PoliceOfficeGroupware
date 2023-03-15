package org.spring.security02.entity;

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
    private Long id;


    private String name;
    private String number;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
