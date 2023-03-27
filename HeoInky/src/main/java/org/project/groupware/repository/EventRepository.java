package org.project.groupware.repository;

import org.project.groupware.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

	@Query(value = "select * from event where event_settle=:eventSettle", nativeQuery = true)
	Page<EventEntity> findEventSettle(Pageable pageable, Long eventSettle);

	@Query(value = "select * from event where event_date between date(:startDate) and date(:endDate)+1", nativeQuery = true)
	Page<EventEntity> findEventDate(Pageable pageable, String startDate, String endDate);

	@Query(value =
					"select * from event where event_date between date(:startDate) and date(:endDate)+1 and event_settle=:eventSettle",
					nativeQuery = true)
	Page<EventEntity> findEventSearch(Pageable pageable, String startDate, String endDate, Long eventSettle);

}
