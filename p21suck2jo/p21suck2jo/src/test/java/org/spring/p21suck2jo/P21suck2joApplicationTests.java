package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class P21suck2joApplicationTests {


	@Autowired
	private PoliceRepository policeRepository;
	@Test
	void contextLoads() {
		PoliceEntity police = new PoliceEntity();
		police.setDept("DEPT1");
		police.setEmail("email@email");
		policeRepository.save(police);

	}

}
