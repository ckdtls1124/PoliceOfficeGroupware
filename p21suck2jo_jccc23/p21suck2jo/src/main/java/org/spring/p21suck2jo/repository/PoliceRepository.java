package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoliceRepository extends JpaRepository<PoliceEntity,Long> {


    PoliceEntity deleteByPoliceId(Long policeId);


    Optional<PoliceEntity> findByPoliceId(Long policeId);

    Optional<PoliceEntity> findByDept(Long deptId);
}
