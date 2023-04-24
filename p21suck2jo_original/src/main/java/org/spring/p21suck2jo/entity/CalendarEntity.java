package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "calender")
public class CalendarEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    //일정 시작시간
    @Column(nullable = false)
    private Date start;

    //일정 종료시간
    @Column(nullable = false)
    private Date end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

}
