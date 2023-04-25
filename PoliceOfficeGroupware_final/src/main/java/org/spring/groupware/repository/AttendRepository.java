package org.spring.groupware.repository;

import org.spring.groupware.entity.AttendEntity;
import org.spring.groupware.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendRepository extends JpaRepository<AttendEntity, Long> {

    List<AttendEntity> findByPolice(PoliceEntity police);
}
