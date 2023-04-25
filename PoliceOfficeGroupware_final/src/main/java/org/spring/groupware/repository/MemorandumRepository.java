package org.spring.groupware.repository;

import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.PoliceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemorandumRepository extends JpaRepository<MemorandumEntity, Long> {
    //List<MemorandumEntity> findAllByPolice(PoliceEntity policeEntity);


    Page<MemorandumEntity> findByMemorandumTitleContaining(String search, Pageable pageable);

    Page<MemorandumEntity> findByPolice(PoliceEntity policeEntity, Pageable pageable);

}
