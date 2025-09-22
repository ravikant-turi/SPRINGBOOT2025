package com.java.sms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiTestController {

	@GetMapping("/index")
	public String index() {
		return "Welcome to the public index!";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin!";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User!";
	}

}
