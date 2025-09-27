package com.micro.plot.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlotConfiguration {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
