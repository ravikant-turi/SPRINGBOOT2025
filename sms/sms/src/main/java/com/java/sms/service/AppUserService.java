package com.java.sms.service;

import java.util.List;

import com.java.sms.model.AppUser;

public interface AppUserService {

	AppUser createUser(AppUser appUser);

	AppUser findUserById(Long id);

	List<AppUser> findAllUsr();
	
	AppUser findUserByUserName(String username);

}
