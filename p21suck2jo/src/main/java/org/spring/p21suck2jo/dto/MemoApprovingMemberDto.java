package org.spring.p21suck2jo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemoApprovingMemberDto {
    private Long approvingMemberId;
    private PoliceEntity police;
    private MemorandumEntity memorandum;
    private int approved;

}
