package com.java.quiz.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityClass {

	@Bean
	ModelMapper modalMapper() {
		return new ModelMapper();
	}

}
