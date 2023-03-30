package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarEntity,Integer> {


}
