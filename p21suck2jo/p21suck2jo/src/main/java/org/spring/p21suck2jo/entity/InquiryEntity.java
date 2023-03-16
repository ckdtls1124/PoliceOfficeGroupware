package org.spring.p21suck2jo.entity;

import lombok.*;
import org.w3c.dom.Text;

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

    @Column(columnDefinition = "TEXT")
    private String inquiryContent;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private MemberEntity member;


//    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private PersonEntity person;
}
