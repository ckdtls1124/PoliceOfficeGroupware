package org.project.groupware.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.project.groupware.entity.EventFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFileEntity, Long> {
}
