package org.spring.security02.entity;

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
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private int phone;
    private String address;


    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<EventEntity> eventList = new ArrayList<>();

    @OneToMany(mappedBy = "person" ,cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();


}
