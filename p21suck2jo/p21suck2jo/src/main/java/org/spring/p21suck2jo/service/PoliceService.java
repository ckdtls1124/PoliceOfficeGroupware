package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;

    private final PasswordEncoder passwordEncoder;

//    public void policeAdd(PoliceDto policeDto){
//
//        PoliceEntity police = PoliceEntity.createOfficer(policeDto,passwordEncoder);
//        PoliceEntity policeSave = policeRepository.save(police);
//
//
//        DeptEntity dept = DeptEntity.deptAdd(policeSave.getDept(),policeSave);
//        deptRepository.save(dept);
//    }

    public Long policeIdSearch(PoliceEntity police){
        Optional<PoliceEntity> policeEntity= policeRepository.findByPoliceId(police.getPoliceId());
        return policeEntity.get().getPoliceId();
    }


}
