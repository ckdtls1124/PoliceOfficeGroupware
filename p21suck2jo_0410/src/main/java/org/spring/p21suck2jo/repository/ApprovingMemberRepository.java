package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.MemoApprovingMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApprovingMemberRepository extends JpaRepository<MemoApprovingMember, Long> {
    Optional<MemoApprovingMember> findByMemorandum(MemorandumEntity memorandumEntity);

    List<MemoApprovingMember> findByPolice(PoliceEntity policeEntity);

    Optional<MemoApprovingMember> findByMemorandumAndPolice(MemorandumEntity memorandumEntity, PoliceEntity policeEntity);
}

