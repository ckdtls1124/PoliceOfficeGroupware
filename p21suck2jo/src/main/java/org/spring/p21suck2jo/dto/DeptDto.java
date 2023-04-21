package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeptDto {

    private Long deptId;
    private String deptName;
    private String deptLocation;
    private int policeNM;
    private List<PoliceEntity> policeList = new ArrayList<>();







}
