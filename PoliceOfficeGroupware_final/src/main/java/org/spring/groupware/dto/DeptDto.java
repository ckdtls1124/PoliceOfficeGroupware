package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.PoliceEntity;

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
