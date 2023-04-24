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
@ToString
@Table(name = "person")
public class PersonEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false)
    private String personName;

    private String personEmail;
    private int personPhone;
    private String personAddress;


    @OneToMany(mappedBy = "eventJoinPerson",cascade = CascadeType.ALL)
    private List<EventEntity> eventList = new ArrayList<>();

    @OneToMany(mappedBy = "person" ,cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();



}
