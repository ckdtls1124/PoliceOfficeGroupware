package org.project.groupware.repository;

import org.project.groupware.entity.EventGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventGroupRepository extends JpaRepository<EventGroupEntity, Long> {
}
