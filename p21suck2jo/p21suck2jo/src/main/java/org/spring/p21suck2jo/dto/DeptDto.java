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


    public static DeptDto deptView(DeptEntity deptEntity){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptId(deptEntity.getDeptId());
        deptDto.setDeptName(deptEntity.getDeptName());
        deptDto.setDeptLocation(deptEntity.getDeptLocation());

        return deptDto;
    }
    public static DeptDto deptView2(DeptEntity deptEntity,int policeNM){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptId(deptEntity.getDeptId());
        deptDto.setDeptName(deptEntity.getDeptName());
        deptDto.setDeptLocation(deptEntity.getDeptLocation());
        deptDto.setPoliceNM(policeNM);

        return deptDto;
    }

}
