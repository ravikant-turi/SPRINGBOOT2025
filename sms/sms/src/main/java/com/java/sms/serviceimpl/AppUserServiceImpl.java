package com.java.sms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.sms.exceptions.ResourceNotFoundException;
import com.java.sms.model.AppUser;
import com.java.sms.repository.UserRepository;
import com.java.sms.service.AppUserService;
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AppUser createUser(AppUser appUser) {
		
		String encodedPassword=this.passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
		return this.userRepository.save(appUser);
	}

	@Override
	public AppUser findUserById(Long id) {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("AppUser is not foudn this id " + id));
	}

	@Override
	public List<AppUser> findAllUsr() {

		return this.userRepository.findAll();
	}

	@Override
	public AppUser findUserByUserName(String username) {

		AppUser foundUser = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("AppUser is not found with this : " + username));

		return foundUser;
	}

}
