package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemorandumFileRepository extends JpaRepository<MemorandumFileEntity, Long> {
    List<MemorandumFileEntity> findAllByMemorandumEntity(MemorandumEntity memorandumEntity);


    void deleteByMemorandumEntity(MemorandumEntity memorandumEntity);
}
