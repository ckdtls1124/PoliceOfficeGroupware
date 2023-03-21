package team.login.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.login.teamproject.entity.TeamEntity;

import java.util.Optional;

public interface WebRepository extends JpaRepository<TeamEntity,Long> {

    Optional<TeamEntity> findByPoliceNumber(int policeNumber);
    Optional<TeamEntity> findByEmailAndPoliceNumber(String email,int policeNumber);
}
