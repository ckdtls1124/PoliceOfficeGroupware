package team.login.teamproject.service;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.login.teamproject.dto.TeamDto;

import team.login.teamproject.entity.TeamEntity;

import team.login.teamproject.repository.WebRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebRepository webRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void inser(TeamDto teamDto) {
        TeamEntity teamEntity = TeamEntity.teamEntity(teamDto, passwordEncoder);
        webRepository.save(teamEntity);
    }

    public TeamDto policeid(int policeNumber) {
        Optional<TeamEntity> teamEntity = webRepository.findByPoliceNumber(policeNumber);
        if (!teamEntity.isPresent()) {
            return null;
        }
        TeamDto teamDto = TeamDto.teamDtoid(teamEntity.get());
        return teamDto;
    }

    public TeamDto policepw(String email, int policeNumber) {

        Optional<TeamEntity> teamEntity = webRepository.findByEmailAndPoliceNumber(email, policeNumber);
        if (!teamEntity.isPresent()) {
            return null;

        } else if (email.equals(teamEntity.get().getEmail()) &&
                            policeNumber == teamEntity.get().getPoliceNumber()) {
            TeamDto teamDto = TeamDto.teamDtopw(teamEntity.get());
            return teamDto;
        }
        return null;
    }
    @Transactional
    public void pwUpdate(TeamDto teamDto) {
        TeamEntity teamEntity=TeamEntity.pwUpdateEntity(teamDto,passwordEncoder);
        webRepository.save(teamEntity);
    }
}




