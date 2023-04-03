package org.spring.chatbot.repository;

import org.spring.chatbot.entity.OfficerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<OfficerEntity, Long> {

	Optional<OfficerEntity> findByName(String name);

}
