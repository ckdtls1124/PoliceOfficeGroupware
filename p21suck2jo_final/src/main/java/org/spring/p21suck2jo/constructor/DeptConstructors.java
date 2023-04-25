package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.entity.DeptEntity;

public class DeptConstructors {

    //부서 생성 set&get 메소드
    public static DeptEntity deptCreate(DeptDto deptDto){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptId(deptDto.getDeptId());
        deptEntity.setDeptName(deptDto.getDeptName());
        deptEntity.setDeptLocation(deptDto.getDeptLocation());
        return deptEntity;
    }

    public static DeptEntity deptUpdate(DeptDto deptDto){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptId(deptDto.getDeptId());
        deptEntity.setDeptName(deptDto.getDeptName());
        deptEntity.setDeptLocation(deptDto.getDeptLocation());
        return deptEntity;
    }


    // view로 보내줄 메소드
    public static DeptDto deptView(DeptEntity deptEntity){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptId(deptEntity.getDeptId());
        deptDto.setDeptName(deptEntity.getDeptName());
        deptDto.setDeptLocation(deptEntity.getDeptLocation());
        return deptDto;
    }

    // view로 보낼 때 부서에 속한 인원수도 같이 보낸다
    public static DeptDto beLongToDept(DeptEntity deptEntity,int policeNM){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptId(deptEntity.getDeptId());
        deptDto.setDeptName(deptEntity.getDeptName());
        deptDto.setDeptLocation(deptEntity.getDeptLocation());
        deptDto.setPoliceNM(policeNM);

        return deptDto;
    }
}
