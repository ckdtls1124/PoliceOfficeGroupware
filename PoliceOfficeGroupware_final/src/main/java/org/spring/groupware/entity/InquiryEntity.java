package org.spring.groupware.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "inquiry")
public class InquiryEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    private String inquiryTitle;

//    @OneToOne
//    private InquiryAnswerEntity inquiryAnswer;

    @Column(columnDefinition = "TEXT")
    private String inquiryContent;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
}
