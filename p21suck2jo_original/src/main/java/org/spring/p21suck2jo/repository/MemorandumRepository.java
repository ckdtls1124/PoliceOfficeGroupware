package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemorandumRepository extends JpaRepository<MemorandumEntity, Long> {
    //List<MemorandumEntity> findAllByPolice(PoliceEntity policeEntity);


    Page<MemorandumEntity> findByMemorandumTitleContaining(String search, Pageable pageable);

    Page<MemorandumEntity> findByPolice(PoliceEntity policeEntity, Pageable pageable);
}
