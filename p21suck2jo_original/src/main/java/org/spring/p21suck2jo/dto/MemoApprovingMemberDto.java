package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoApprovingMemberDto {
    private Long approvingMemberId;
    private PoliceEntity police;
    private MemorandumEntity memorandum;
    private int approved;

}
