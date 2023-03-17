package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Calendar")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Calendar_id")
    private Long calendarId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}
