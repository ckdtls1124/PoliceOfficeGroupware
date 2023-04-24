package org.spring.p21suck2jo.chatbot.repository;

import org.spring.p21suck2jo.chatbot.entity.OfficerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<OfficerEntity, Long> {

	Optional<OfficerEntity> findByOfficerName(String name);

}
