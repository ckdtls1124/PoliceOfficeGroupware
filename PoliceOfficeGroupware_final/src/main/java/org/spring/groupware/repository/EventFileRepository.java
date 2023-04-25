package org.spring.groupware.repository;

import org.spring.groupware.entity.EventFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFileEntity, Long> {
}
