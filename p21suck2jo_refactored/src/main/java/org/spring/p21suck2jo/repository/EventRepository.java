package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

	@Query(value = "select police_id from police_officer where email=:nowPolice", nativeQuery = true)
	Long findByEmail(String nowPolice);

	@Query(value = "select * from event where police_id=:policeId", nativeQuery = true)
	Page<EventEntity> findMyEvent(Pageable pageable, Long policeId);

	@Query(value = "select * from event where event_date > current_date()", nativeQuery = true)
	List<EventEntity> findTodayEvent();
}
