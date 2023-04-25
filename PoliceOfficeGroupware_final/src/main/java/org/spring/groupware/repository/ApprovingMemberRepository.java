package org.spring.groupware.repository;

import org.spring.groupware.entity.ApprovingMemberAllEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovingMemberRepository extends JpaRepository<ApprovingMemberAllEntity, Long> {

}
