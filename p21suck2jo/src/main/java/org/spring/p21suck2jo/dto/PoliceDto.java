package org.spring.p21suck2jo.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.role.Role;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceDto {

    private Long policeId;


    private String email;
    private String password;
    private String policeName;
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table
    private Role role; //권한
    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private String policePhone;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private DeptEntity dept;

    public static PoliceDto officerView(PoliceEntity policeEntity){
        PoliceDto police = new PoliceDto();
        police.setPoliceId(policeEntity.getPoliceId());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
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
    public static PoliceDto teamDtoid(PoliceEntity policeEntity) {

        PoliceDto policeDto = new PoliceDto();
        policeDto.setEmail(policeEntity.getEmail());
        policeDto.setPoliceNumber(policeEntity.getPoliceNumber());
        return policeDto;
    }

    public static PoliceDto teamDtopw(PoliceEntity policeEntity) {

        PoliceDto policeDto=new PoliceDto();

        policeDto.setPoliceId(policeEntity.getPoliceId());
        policeDto.setEmail(policeEntity.getEmail());
        policeDto.setPassword(policeEntity.getPassword());
        policeDto.setPoliceName(policeEntity.getPoliceName());
        policeDto.setPoliceNumber(policeEntity.getPoliceNumber());
        policeDto.setPoliceAddress(policeEntity.getPoliceAddress());
        policeDto.setDetailAddress(policeEntity.getDetailAddress());
        policeDto.setPolicePhone(policeEntity.getPolicePhone());
        policeDto.setCreateTime(policeEntity.getCreateTime());
//        policeDto.setUpdateTime(policeEntity.getUpdateTime());
        return policeDto;
    }

    public static PoliceDto toDtoName(PoliceEntity policeEntity) {
        PoliceDto policeDto=new PoliceDto();

        policeDto.setPoliceName(policeEntity.getPoliceName());

        return  policeDto;

    }

    public static PoliceDto toDtoId(PoliceEntity policeEntity) {
        PoliceDto policeDto=new PoliceDto();

        policeDto.setPoliceId(policeEntity.getPoliceId());
        policeDto.setPoliceName(policeEntity.getPoliceName());

        return  policeDto;

    }
}
