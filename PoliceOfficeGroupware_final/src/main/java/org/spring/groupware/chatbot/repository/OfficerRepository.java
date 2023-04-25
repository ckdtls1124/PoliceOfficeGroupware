package org.spring.groupware.chatbot.repository;

import org.spring.groupware.chatbot.entity.OfficerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<OfficerEntity, Long> {

	Optional<OfficerEntity> findByOfficerName(String name);

}
