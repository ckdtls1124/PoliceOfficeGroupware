package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
