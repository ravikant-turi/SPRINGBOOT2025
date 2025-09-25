package com.micro.account.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountGeneralConfiguration {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}