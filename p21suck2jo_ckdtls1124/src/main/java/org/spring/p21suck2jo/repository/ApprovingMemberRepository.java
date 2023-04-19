package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.ApprovingMemberAllEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovingMemberRepository extends JpaRepository<ApprovingMemberAllEntity, Long> {

}
