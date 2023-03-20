package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoliceRepository extends JpaRepository<PoliceEntity,Long> {

<<<<<<< HEAD
    Optional<PoliceEntity> findByPoliceId(Long id);


    PoliceEntity deleteByPoliceId(Long policeId);
=======

    Optional<PoliceEntity> findByPoliceId(Long policeId);
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
}
