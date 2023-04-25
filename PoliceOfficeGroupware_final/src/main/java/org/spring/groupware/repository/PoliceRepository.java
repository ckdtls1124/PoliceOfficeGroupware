package org.spring.groupware.repository;

import org.spring.groupware.entity.DeptEntity;
import org.spring.groupware.entity.PoliceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PoliceRepository extends JpaRepository<PoliceEntity,Long> {

    Optional<PoliceEntity> findByPoliceNumber(int policeNumber);
    Optional<PoliceEntity> findByEmailAndPoliceNumber(String email,int policeNumber);


    boolean existsByEmail(String email);
    boolean existsByPoliceNumber(int policeNumber);
    Optional<PoliceEntity> findByEmail(String email);


    Optional<PoliceEntity> findByPoliceId(Long policeId);



    @Query(value = " select * from police_officer p inner join dept d " +
            "on p.dept_id=d.dept_id " +
            "where p.dept_id=:deptId " ,nativeQuery = true )
    List<PoliceEntity> findAlldeptId(@Param("deptId") Long deptId);


    List<PoliceEntity> findByDept(DeptEntity i);

    Optional<PoliceEntity> findByPoliceName(String policeName);


    Page<PoliceEntity> findByPoliceNameContaining(Pageable pageable, String search);
}
