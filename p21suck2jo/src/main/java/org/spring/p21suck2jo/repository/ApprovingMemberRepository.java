package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.ApprovingMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApprovingMemberRepository extends JpaRepository<ApprovingMember, Long> {
    Optional<ApprovingMember> findByMemorandum(MemorandumEntity memorandumEntity);

    List<ApprovingMember> findByPolice(PoliceEntity policeEntity);
}

