package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
=======
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.PoliceService;
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest

class P21suck2joApplicationTests {

<<<<<<< HEAD
	@Autowired
	private PoliceRepository policeRepository;
=======

	@Autowired
	private PoliceRepository policeRepository;
	@Test
	void contextLoads() {
		PoliceEntity police = new PoliceEntity();
		police.setDept("DEPT1");
		police.setEmail("email@email");
		policeRepository.save(police);

	}
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db

	@Test
	@Transactional
	void contextLoads() {

		PoliceEntity policeEntity = new PoliceEntity();
		policeEntity.setPoliceName("이름1");
		policeEntity.setPoliceNumber(111);
		policeEntity.setPassword("password1");
		policeRepository.save(policeEntity);

		Optional<PoliceEntity> police= policeRepository.findByPoliceId(policeEntity.getPoliceId());
		PoliceEntity policeEntity1 = police.get();
		System.out.println(policeEntity1+"<--가져온거");
		policeRepository.delete(policeEntity1);
	}
	@Test
	void memberSearch(){

	}
}
