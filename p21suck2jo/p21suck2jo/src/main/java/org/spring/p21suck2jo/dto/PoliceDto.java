package org.spring.p21suck2jo.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceDto {

    private Long policeId;
    private String password;
    private String dept;
    private String policeName;
    private String email;
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table
    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private String policePhone;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public static PoliceDto officerView(PoliceEntity policeEntity){
        PoliceDto police = new PoliceDto();
        police.setPoliceId(policeEntity.getPoliceId());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        police.setPassword(policeEntity.getPassword());
        police.setPoliceName(policeEntity.getPoliceName());
        police.setDept(policeEntity.getDept());
        police.setEmail(policeEntity.getEmail());
        police.setPoliceNumber(policeEntity.getPoliceNumber());
        police.setRanks(policeEntity.getRanks());
        police.setZip_code(policeEntity.getZip_code());
        police.setPoliceAddress(policeEntity.getPoliceAddress());
        police.setDetailAddress(policeEntity.getDetailAddress());
        police.setPolicePhone(policeEntity.getPolicePhone());
        police.setCreateTime(policeEntity.getCreateTime());
        return police;
    }

}
