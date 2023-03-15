package org.spring.security02.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "event")
public class EventEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private int number; //사건번호
    private String content; //사건내용

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
}
