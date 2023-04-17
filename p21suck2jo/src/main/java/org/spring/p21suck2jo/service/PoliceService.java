package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.constructor.PoliceConstructors;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;
    private final PasswordEncoder passwordEncoder;


    //경찰관 추가
    public void policeAdd(PoliceDto policeDto){
        PoliceEntity police = PoliceConstructors.dtoToEntityPasswordEncryp(policeDto,passwordEncoder);
        policeRepository.save(police);
    }

    //경찰관 전체 목록
    public List<PoliceDto> policeList(){
        List<PoliceDto> policeList = new ArrayList<>();
        List<PoliceEntity> policesSearch = policeRepository.findAll();

        for(PoliceEntity polices : policesSearch){
            policeList.add(PoliceConstructors.entityToDto(polices));
        }
        return policeList;
    }

    // id(PK)로 경찰관 상세정보 조회
    public PoliceDto policeDetail(Long id) {
        Optional<PoliceEntity> policeIdSearch = policeRepository.findByPoliceId(id);
        return PoliceConstructors.entityToDto(policeIdSearch.get());

    }

    //email 로 경찰관 상세정보 조회
    public PoliceDto policeEmailSearch(String email){
        Optional<PoliceEntity> police= policeRepository.findByEmail(email);
        PoliceEntity policeEntity= police.get();
        return PoliceConstructors.entityToDto(policeEntity);
    }

    //내 정보 수정
    public void myPageUpdate(PoliceDto policeDto){

        Optional<PoliceEntity> optionalPolice = policeRepository.findByPoliceId(policeDto.getPoliceId());
        if (optionalPolice.isPresent()) {
            PoliceEntity police = optionalPolice.get();
            police.myPageUpdate(policeDto);
            policeRepository.save(police);
        }
    }

    //관리자 회원수정
    public void updatePolice(PoliceDto policeDto){
        Optional<PoliceEntity> optionalPolice = policeRepository.findByPoliceId(policeDto.getPoliceId());
        if(optionalPolice.isPresent()){
            PoliceEntity police= optionalPolice.get();
            police.updatePolice(policeDto,passwordEncoder);
            policeRepository.save(police);
        }
    }

   //관리자 경찰관 정보 삭제
    public void policeDelete(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        PoliceEntity policeEntity = policeIdSearch.get();

        policeRepository.delete(policeEntity);

    }



}
