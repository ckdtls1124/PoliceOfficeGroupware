package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;
import org.spring.p21suck2jo.controller.PoliceController;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.DeptService;
import org.spring.p21suck2jo.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import static org.spring.p21suck2jo.role.Role.ADMIN;

@SpringBootTest
class P21suck2joApplicationTests {

	@Autowired
	private PoliceRepository policeRepository;
	private PoliceController policeController;


}
