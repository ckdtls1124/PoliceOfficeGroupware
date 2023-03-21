package team.login.teamproject.dto;

import lombok.*;
import team.login.teamproject.entity.TeamEntity;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeamDto {

    private Long policeId;


    private String email;

    private String password;

    private int policeNumber;

//    private String policeNumber;
    public static TeamDto teamDtoid(TeamEntity teamEntity) {
        TeamDto teamDto = new TeamDto();
        teamDto.setEmail(teamEntity.getEmail());
        teamDto.setPoliceNumber(teamEntity.getPoliceNumber());
    return teamDto;
    }

    public static TeamDto teamDtopw(TeamEntity teamEntity) {
        TeamDto teamDto=new TeamDto();
        teamDto.setPoliceId(teamEntity.getPoliceId());
        teamDto.setEmail(teamEntity.getEmail());
        teamDto.setPassword(teamEntity.getPassword());
        teamDto.setPoliceNumber(teamEntity.getPoliceNumber());
        return teamDto;
    }
}

