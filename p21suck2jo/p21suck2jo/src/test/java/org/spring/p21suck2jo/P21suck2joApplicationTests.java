package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
class P21suck2joApplicationTests {

	@Autowired
	private PoliceRepository policeRepository;

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
