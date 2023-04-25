package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PoliceConstructors {

    public static PoliceEntity dtoToEntityPasswordEncryp(PoliceDto policeDto, PasswordEncoder passwordEncoder){ //test 끝나면 passwordEncoder
        PoliceEntity police = new PoliceEntity();
        police.setPoliceId(policeDto.getPoliceId());
        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
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


    public static PoliceDto entityToDto(PoliceEntity policeEntity){
        PoliceDto police = new PoliceDto();
        police.setPoliceId(policeEntity.getPoliceId());
        police.setPassword(policeEntity.getPassword());
        police.setPoliceName(policeEntity.getPoliceName());
        police.setEmail(policeEntity.getEmail());
        police.setPoliceNumber(policeEntity.getPoliceNumber());
        police.setRanks(policeEntity.getRanks());
        police.setRole(policeEntity.getRole());
        police.setZip_code(policeEntity.getZip_code());
        police.setPoliceAddress(policeEntity.getPoliceAddress());
        police.setDetailAddress(policeEntity.getDetailAddress());
        police.setPolicePhone(policeEntity.getPolicePhone());
        police.setCreateTime(policeEntity.getCreateTime());
        police.setDept(policeEntity.getDept());
        return police;
    }




}