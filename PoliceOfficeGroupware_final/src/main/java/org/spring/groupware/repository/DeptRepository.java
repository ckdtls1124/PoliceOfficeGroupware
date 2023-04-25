package org.spring.groupware.repository;

import org.spring.groupware.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<DeptEntity,Long> {


    DeptEntity findByDeptId(Long id);
}
