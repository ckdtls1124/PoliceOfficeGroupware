package org.spring.p21suck2jo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EventFileConfigClass implements WebMvcConfigurer {

	String saveEventFiles = "file:///C:/saveEventFiles/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations(saveEventFiles);
	}

}
