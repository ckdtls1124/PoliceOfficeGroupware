package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.ApprovingMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

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

    public static MemorandumDto toMemorandumDto(MemorandumEntity memorandumEntity){

        MemorandumDto memorandumDto = new MemorandumDto();

        memorandumDto.setMemorandumTitle(memorandumEntity.getMemorandumTitle());
        memorandumDto.setMemorandumContent(memorandumEntity.getMemorandumContent());
        memorandumDto.setApproval(memorandumEntity.getApproval());
        memorandumDto.setPolice(memorandumEntity.getPolice());
        memorandumDto.setApprovingMemberList(memorandumEntity.getApprovingMemberList());

        return memorandumDto;
    }

}
