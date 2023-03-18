package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "calender")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calender_id")
    private Long calenderId;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

}
