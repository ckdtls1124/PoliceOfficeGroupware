package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;
    private final PoliceRepository policeRepository;


    public void deptInsert(DeptDto deptDto){
      deptRepository.save(DeptEntity.deptCreate(deptDto));
    }


    public List<DeptDto> deptList(){
        List<DeptDto> deptDtoList = new ArrayList<>();
        List<DeptEntity> list= deptRepository.findAll();

        for(DeptEntity deptEntity : list){

            deptDtoList.add(DeptDto.deptView2(deptEntity,deptEntity.getPoliceList().size()));
        }
        return deptDtoList;
    }

    public DeptDto deptId(Long id){
        DeptEntity dept=deptRepository.findByDeptId(id);

        return DeptDto.deptView2(dept,dept.getPoliceList().size());

    }


    public List<PoliceDto> list2(Long deptId) {

        List<PoliceEntity> policeEntities = policeRepository.findAlldeptId(deptId);
        List<PoliceDto> policeDtos = new ArrayList<>();

        for (PoliceEntity policeEntity : policeEntities) {
            PoliceDto policeDto = PoliceDto.officerView(policeEntity);
            policeDtos.add(policeDto);
        }

        return policeDtos;
    }


}
