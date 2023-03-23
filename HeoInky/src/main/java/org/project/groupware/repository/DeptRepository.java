package org.project.groupware.repository;

import org.project.groupware.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<DeptEntity, Long> {
}
