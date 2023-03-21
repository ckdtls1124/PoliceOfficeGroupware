package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.DeptEntity;
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

    private String deptName;
    private String deptRanks;
    private int deptPoliceNumber;

    private PoliceEntity police;

    public static DeptDto deptView(DeptEntity deptEntity){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptId(deptEntity.getDeptId());
        deptDto.setDeptName(deptEntity.getDeptName());
        deptDto.setDeptRanks(deptEntity.getDeptRanks());
        deptDto.setDeptPoliceNumber(deptEntity.getDeptPoliceNumber());
        deptDto.setPolice(deptEntity.getPolice());
        return deptDto;
    }

}
