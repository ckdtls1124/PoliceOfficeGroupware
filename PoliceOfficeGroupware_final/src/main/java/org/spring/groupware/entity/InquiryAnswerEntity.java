package org.spring.groupware.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="inquiryAnswer")
public class InquiryAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiryAnswer_id")
    private Long inquiryAnswerId;

    @Column(columnDefinition = "TEXT")
    private String inquiryAnswerContent;

    @OneToOne
    private InquiryEntity inquiryEntity;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;
}
