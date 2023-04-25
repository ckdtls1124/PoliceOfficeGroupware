package org.spring.groupware.repository;

import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.MemorandumFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemorandumFileRepository extends JpaRepository<MemorandumFileEntity, Long> {
    List<MemorandumFileEntity> findAllByMemorandumEntity(MemorandumEntity memorandumEntity);


    void deleteByMemorandumEntity(MemorandumEntity memorandumEntity);
}
