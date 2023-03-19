package org.spring.p21suck2jo;

import org.spring.p21suck2jo.configuration.FileUploadProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
public class P21suck2joApplication {

	public static void main(String[] args) {
		SpringApplication.run(P21suck2joApplication.class, args);
	}

}
