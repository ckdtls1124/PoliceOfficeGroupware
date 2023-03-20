package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;
<<<<<<< HEAD
//    private final PasswordEncoder passwordEncoder;

    public void policeAdd(PoliceDto policeDto){
        PoliceEntity police = PoliceEntity.createOfficer(policeDto);
        PoliceEntity policeInsert=policeRepository.save(police);

        DeptEntity deptEntity = DeptEntity.deptCreate(policeInsert.getDept(),policeInsert.getPoliceName(),policeInsert);
        deptRepository.save(deptEntity);

    }

    public List<PoliceDto> policeList(){
        List<PoliceDto> policeList = new ArrayList<>();
        List<PoliceEntity> policesSearch = policeRepository.findAll();

        for(PoliceEntity polices : policesSearch){
            policeList.add(PoliceDto.officerView(polices));
        }
        return policeList;
    }

    public PoliceDto policeDetail(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        return PoliceDto.officerView(policeIdSearch.get());

=======

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
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db

    public Long policeIdSearch(PoliceEntity police){
        Optional<PoliceEntity> policeEntity= policeRepository.findByPoliceId(police.getPoliceId());
        return policeEntity.get().getPoliceId();
    }

<<<<<<< HEAD
//    public void policeUpdate(Long id){
//        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
//        PoliceEntity police = policeIdSearch.get();
//        policeRepository.save(police);
//    }

        public void policeUpdate(PoliceDto policeDto){
            PoliceEntity policeEntity = PoliceEntity.createOfficer(policeDto);
            policeRepository.save(policeEntity);
    }

    public void policeDelete(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        PoliceEntity policeEntity = policeIdSearch.get();

         policeRepository.delete(policeEntity);

    }
=======

>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
}
