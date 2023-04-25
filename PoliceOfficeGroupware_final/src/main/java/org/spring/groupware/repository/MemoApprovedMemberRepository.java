package org.spring.groupware.repository;

import org.spring.groupware.entity.MemoApprovedMember;
import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoApprovedMemberRepository extends JpaRepository<MemoApprovedMember, Long> {
    Optional<MemoApprovedMember> findMemoApprovedMemberByMemorandum(MemorandumEntity memorandumEntity);

    List<MemoApprovedMember> findMemoApprovedMemberByPolice(PoliceEntity policeEntity);

    Optional<MemoApprovedMember> findMemoApprovedMemberByMemorandumAndPolice(MemorandumEntity memorandumEntity, PoliceEntity policeEntity);
}

