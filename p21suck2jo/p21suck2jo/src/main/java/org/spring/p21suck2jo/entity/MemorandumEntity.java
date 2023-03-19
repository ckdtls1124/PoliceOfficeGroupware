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
@Table(name = "memorandum")
@Builder
public class MemorandumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memorandum_id")
    private Long memorandumId;


    private String memorandumTitle;

    @Column(columnDefinition = "TEXT")
    private String memorandumContent;


//    승인 여부 확인
//    private int approval;



    @ManyToOne
    @JoinColumn(name = "policeId")
    private PoliceEntity policeEntity;

//    승인을 해주는 member의 list를 가진다.
    @OneToMany
    private List<PoliceEntity> apprvoingPolice = new ArrayList<>();

}
