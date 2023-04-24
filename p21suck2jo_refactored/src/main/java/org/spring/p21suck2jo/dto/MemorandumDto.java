package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.MemoApprovedMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

import java.time.LocalDateTime;
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

    private LocalDateTime createTime;


    private PoliceEntity police;
    
    List<MemoApprovedMember> approvingMemberList = new ArrayList<>();

    public static MemorandumDto toMemorandumDto(MemorandumEntity memorandumEntity){

        MemorandumDto memorandumDto = new MemorandumDto();

        memorandumDto.setMemorandumId(memorandumEntity.getMemorandumId());
        memorandumDto.setMemorandumTitle(memorandumEntity.getMemorandumTitle());
        memorandumDto.setMemorandumContent(memorandumEntity.getMemorandumContent());
        memorandumDto.setPolice(memorandumEntity.getPolice());
        memorandumDto.setCreateTime(memorandumEntity.getCreateTime());
        memorandumDto.setApprovingMemberList(memorandumEntity.getApprovingMemberList());

        return memorandumDto;
    }

}
