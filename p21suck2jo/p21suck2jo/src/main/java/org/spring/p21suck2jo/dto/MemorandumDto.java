package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.ApprovingMember;
import org.spring.p21suck2jo.entity.PoliceEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemorandumDto {

    private Long memorandumId;

    private String memorandumTitle;

    private String memorandumContent;


    //    승인 여부 확인
    private int approval;

    private PoliceEntity police;
    
    List<ApprovingMember> approvingMemberList = new ArrayList<>(); 

}
