package org.spring.groupware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class OrgUnitAllowedMember {
    private String userId;
    private String userExternalKey;
}
