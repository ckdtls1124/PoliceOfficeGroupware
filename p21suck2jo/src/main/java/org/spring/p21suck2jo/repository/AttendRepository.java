package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.AttendEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendRepository extends JpaRepository<AttendEntity, Long> {

    List<AttendEntity> findByPolice(PoliceEntity police);
}
