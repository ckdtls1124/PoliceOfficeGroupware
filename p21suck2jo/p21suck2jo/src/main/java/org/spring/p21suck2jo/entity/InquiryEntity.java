package org.spring.security02.entity;

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
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
}
