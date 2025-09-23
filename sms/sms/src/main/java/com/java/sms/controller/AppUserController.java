package com.java.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.sms.model.AppUser;
import com.java.sms.service.AppUserService;

@RestController
@RequestMapping("/api/AppUser")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@PostMapping
	public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser appUser) {
		AppUser createUser = this.appUserService.createUser(appUser);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<AppUser> findAppUserById(@PathVariable("id") Long id) {

		AppUser userFound = this.appUserService.findUserById(id);

		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@GetMapping("user/{username}")
	public ResponseEntity<AppUser> findAppUserByUserName(@PathVariable("username") String username) {

		AppUser userFound = this.appUserService.findUserByUserName(username);

		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AppUser>> findAllAppUser() {
		List<AppUser> appUsers = this.appUserService.findAllUsr();

		return new ResponseEntity<>(appUsers, HttpStatus.OK);
	}

}
