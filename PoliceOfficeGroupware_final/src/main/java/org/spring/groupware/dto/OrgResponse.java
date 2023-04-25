package org.spring.groupware.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrgResponse {
    private List<OrgUnit> orgUnits;
    private ResponseMetaData responseMetaData;

}
