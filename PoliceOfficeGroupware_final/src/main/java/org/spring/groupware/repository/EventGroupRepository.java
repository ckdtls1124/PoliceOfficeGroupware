package org.spring.groupware.repository;

import org.spring.groupware.entity.EventGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventGroupRepository extends JpaRepository<EventGroupEntity, Long> {
}
