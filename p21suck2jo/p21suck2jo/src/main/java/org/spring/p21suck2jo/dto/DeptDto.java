package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.PoliceEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeptDto {


    private Long deptId;
    private String deptNM;

    private PoliceEntity police;




}
