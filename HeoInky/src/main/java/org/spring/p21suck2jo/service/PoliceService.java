package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void policeAdd(PoliceDto policeDto){
        PoliceEntity police = PoliceService.createOfficer(policeDto,passwordEncoder);
        policeRepository.save(police);

    }

    public List<PoliceDto> policeList(){
        List<PoliceDto> policeList = new ArrayList<>();
        List<PoliceEntity> policesSearch = policeRepository.findAll();

        for(PoliceEntity polices : policesSearch){
            policeList.add(PoliceDto.officerView(polices));
        }
        return policeList;
    }

    public PoliceDto policeDetail(Long id) {
        Optional<PoliceEntity> policeIdSearch = policeRepository.findByPoliceId(id);
        return PoliceDto.officerView(policeIdSearch.get());

    }

    //회원수정(myPage)
    public void policeUpdate(PoliceDto policeDto){
        PoliceEntity police=   PoliceService.createOfficer(policeDto, passwordEncoder);
        policeRepository.save(police);
    }



    public void policeDelete(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        PoliceEntity policeEntity = policeIdSearch.get();

        policeRepository.delete(policeEntity);

    }

    public PoliceDto policeEmailSearch(String email){
        Optional<PoliceEntity> police= policeRepository.findByEmail(email);
        PoliceEntity policeEntity= police.get();
        return PoliceDto.officerView(policeEntity);
    }

    public static PoliceEntity createOfficer(PoliceDto policeDto, PasswordEncoder passwordEncoder){ //test 끝나면 passwordEncoder

        PoliceEntity police = new PoliceEntity();
        police.setPoliceId(policeDto.getPoliceId());
        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
//        police.setPassword(policeDto.getPassword());
        police.setPoliceName(policeDto.getPoliceName());
        police.setEmail(policeDto.getEmail());
        police.setPoliceNumber(policeDto.getPoliceNumber());
        police.setRanks(policeDto.getRanks());
        police.setRole(policeDto.getRole());
        police.setZip_code(policeDto.getZip_code());
        police.setPoliceAddress(policeDto.getPoliceAddress());
        police.setDetailAddress(policeDto.getDetailAddress());
        police.setPolicePhone(policeDto.getPolicePhone());
        police.setCreateTime(policeDto.getCreateTime());
        police.setDept(policeDto.getDept());
        return police;
    }


    public PoliceDto findByPoliceName(String email) {

        Optional<PoliceEntity> policeEntity=policeRepository.findByEmail(email);

        if(policeEntity.isPresent()){
            return  PoliceDto.toDtoName(policeEntity.get());
        }

        return null;

    }
    public PoliceDto findByPoliceIdAndName(String email) {

        Optional<PoliceEntity> policeEntity=policeRepository.findByEmail(email);

        if(policeEntity.isPresent()){
            return  PoliceDto.toDtoId(policeEntity.get());
        }

        return null;

    }

}
