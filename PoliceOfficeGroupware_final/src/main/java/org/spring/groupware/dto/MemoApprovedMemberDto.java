package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.PoliceEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoApprovedMemberDto {
    private Long approvedMemberId;
    private PoliceEntity police;
    private MemorandumEntity memorandum;
    private int approved;

}
