package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemorandumRepository extends JpaRepository<MemorandumEntity, Long> {
    List<MemorandumEntity> findAllByPolice(PoliceEntity policeEntity);



}
