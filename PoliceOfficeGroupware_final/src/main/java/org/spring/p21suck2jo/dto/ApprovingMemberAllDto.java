package org.spring.p21suck2jo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.p21suck2jo.entity.ApprovingMemberAllEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApprovingMemberAllDto {
    private String policeName;
    private String deptName;

    public static List<ApprovingMemberAllDto> entityToDto(List<ApprovingMemberAllEntity> approvingMemberAllEntityList){
        List<ApprovingMemberAllDto> approvingMemberAllDtoList = new ArrayList<>();
        for(ApprovingMemberAllEntity eachApprovingMemberEntity : approvingMemberAllEntityList){
            ApprovingMemberAllDto approvingMemberAllDto = new ApprovingMemberAllDto();
            approvingMemberAllDto.setPoliceName(eachApprovingMemberEntity.getPoliceName());
            approvingMemberAllDto.setDeptName(eachApprovingMemberEntity.getDeptName());

            approvingMemberAllDtoList.add(approvingMemberAllDto);
        }

        return approvingMemberAllDtoList;
    }


}
