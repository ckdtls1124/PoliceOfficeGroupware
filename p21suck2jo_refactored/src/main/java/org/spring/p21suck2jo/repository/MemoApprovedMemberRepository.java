package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.MemoApprovedMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoApprovedMemberRepository extends JpaRepository<MemoApprovedMember, Long> {
    Optional<MemoApprovedMember> findMemoApprovedMemberByMemorandum(MemorandumEntity memorandumEntity);

    List<MemoApprovedMember> findMemoApprovedMemberByPolice(PoliceEntity policeEntity);

    Optional<MemoApprovedMember> findMemoApprovedMemberByMemorandumAndPolice(MemorandumEntity memorandumEntity, PoliceEntity policeEntity);
}

