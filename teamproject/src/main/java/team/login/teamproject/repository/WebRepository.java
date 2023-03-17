package team.login.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.login.teamproject.entity.WebEntity;

public interface WebRepository extends JpaRepository<WebEntity,Long> {
}
