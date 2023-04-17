package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.constructor.DeptConstructors;
import org.spring.p21suck2jo.constructor.PoliceConstructors;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;
    private final PoliceRepository policeRepository;

    //부서생성
    public void deptInsert(DeptDto deptDto){
      deptRepository.save(DeptConstructors.deptCreate(deptDto));
    }

    //부서 전체목록
    public List<DeptDto> deptList(){
        List<DeptDto> deptDtoList = new ArrayList<>();
        List<DeptEntity> list= deptRepository.findAll();
        for(DeptEntity deptEntity : list){
            deptDtoList.add(DeptConstructors.beLongToDept(deptEntity,deptEntity.getPoliceList().size()));
        }
        return deptDtoList;
    }

    //부서 상세목록
    public List<PoliceDto> deptDetail(Long deptId) {
        List<PoliceEntity> policeEntities = policeRepository.findAlldeptId(deptId);
        List<PoliceDto> policeDtos = new ArrayList<>();

        for (PoliceEntity policeEntity : policeEntities) {
            PoliceDto policeDto = PoliceConstructors.entityToDto(policeEntity);
            policeDtos.add(policeDto);
        }
        return policeDtos;
    }

    //PK(id)로 특정 부서조회
    public DeptDto beLongToDept(Long id){
        DeptEntity dept=deptRepository.findByDeptId(id);
        return DeptConstructors.beLongToDept(dept,dept.getPoliceList().size());
    }

    //부서 수정
    public void deptUpdate(DeptDto deptDto){
       DeptEntity dept= DeptConstructors.deptUpdate(deptDto);
        deptRepository.save(dept);
    }

    //부서 삭제
    public void deptDelete(Long id){
         DeptEntity deptEntity = deptRepository.findByDeptId(id);
         deptRepository.delete(deptEntity);
    }



}
