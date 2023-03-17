package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.BoardEntity;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceDto {

    private Long policeId;
    private String password;
    private String email;
    private int poilceNumber; //사원번호
    private String ranks; //직책 <- table
    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private int policePhone;


}
