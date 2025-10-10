package com.micro.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroAccountApplication.class, args);
	}

}
