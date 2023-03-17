package org.spring.p21suck2jo.entity;

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
    private Long eventId;

    private int eventNumber; //사건번호
    private String eventContent; //사건내용

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
}
