package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PoliceRepository extends JpaRepository<PoliceEntity,Long> {





    Optional<PoliceEntity> findByPoliceId(Long policeId);


//    List<PoliceEntity> findAllByDept(Long deptId);
//
//    List<PoliceEntity> findAllByDeptId(Long deptId);


    @Query(value = " select * from police_officer p inner join dept d " +
            "on p.dept_id=d.dept_id " +
            "where p.dept_id=:deptId " ,nativeQuery = true )
    List<PoliceEntity> findAlldeptId(@Param("deptId") Long deptId);
}
