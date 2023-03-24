package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<DeptEntity,Long> {


    DeptEntity findByDeptId(Long id);
}
