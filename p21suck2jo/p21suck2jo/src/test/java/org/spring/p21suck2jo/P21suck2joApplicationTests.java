package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;

import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;

import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.PoliceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class P21suck2joApplicationTests {


	@Autowired
	private PoliceRepository policeRepository;
	@Autowired
	private DeptRepository deptRepository;


	@Test
	void contextLoads() {
//		DeptEntity deptEntity = new DeptEntity();
//		deptEntity.setDeptId(1L);
//		deptEntity.setDeptName("부서1");
//		deptEntity.setDeptLocation("부서위치1");
//		deptRepository.save(deptEntity);
//
//		PoliceEntity police = new PoliceEntity();
//		police.setPoliceName("이름1");
//		police.setPassword("1111");
//		police.setDept(deptEntity);
//		policeRepository.save(police);
//
//		Optional<DeptEntity> dept = deptRepository.findByDeptId(deptEntity.getDeptId());
//
//		if (dept.isPresent()) {
//			int a = dept.get().getPoliceList().size();
//			System.out.println(a+"<----");
//		} else {
//			System.out.println("DeptEntity not found");
//		}
	}

	}

