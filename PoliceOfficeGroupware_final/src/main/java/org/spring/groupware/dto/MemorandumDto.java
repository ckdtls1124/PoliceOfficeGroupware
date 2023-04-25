package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.MemoApprovedMember;
import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.PoliceEntity;

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
