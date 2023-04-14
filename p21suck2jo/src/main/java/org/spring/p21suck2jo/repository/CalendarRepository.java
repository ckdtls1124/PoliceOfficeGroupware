package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity,Integer> {


    @Query(value = "select *" +
            " from calender c join police_officer p " +
            " on c.police_id = p.police_id " +
            " where c.police_id =:id " ,nativeQuery = true)
    List<CalendarEntity> findByPoliceId(@Param("id") Long id);


	@Query(value = "select * " +
					" from calender c join police_officer p " +
					" on c.police_id = p.police_id " +
					" where c.police_id =:id and DATE(c.start) = CURDATE() ",nativeQuery = true)
	List<CalendarEntity> findByTodayWorks(Long id);
}
