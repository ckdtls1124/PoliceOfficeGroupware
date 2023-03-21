package team.login.teamproject.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.login.teamproject.dto.TeamDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "police_officer")
public class TeamEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long policeId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false, unique = true)
    private int policeNumber; //사원번호
//    private String policeNumber;

    public static TeamEntity teamEntity(TeamDto teamDto,
                                        PasswordEncoder passwordEncoder) {
        TeamEntity teamEntity = new TeamEntity();

        System.out.println(teamDto.getPassword() + " >>> ");

        teamEntity.setPoliceId(teamDto.getPoliceId());
        teamEntity.setEmail(teamDto.getEmail());
        teamEntity.setPassword(passwordEncoder.encode(teamDto.getPassword()));
        teamEntity.setPoliceNumber(teamDto.getPoliceNumber());
        return teamEntity;
    }

    public static TeamEntity pwUpdateEntity(TeamDto teamDto, PasswordEncoder passwordEncoder) {
        TeamEntity teamEntity = new TeamEntity();

        teamEntity.setPoliceId(teamDto.getPoliceId());
        teamEntity.setEmail(teamDto.getEmail());
        teamEntity.setPoliceNumber(teamDto.getPoliceNumber());
        teamEntity.setPassword(teamDto.getPassword());
        return teamEntity;
    }

}
