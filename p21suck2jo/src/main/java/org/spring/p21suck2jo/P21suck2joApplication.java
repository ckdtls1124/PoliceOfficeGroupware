package org.spring.p21suck2jo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class P21suck2joApplication {

	public static void main(String[] args) {
		SpringApplication.run(P21suck2joApplication.class, args);
	}
}
