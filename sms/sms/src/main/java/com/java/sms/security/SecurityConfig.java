package com.java.sms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.java.sms.exceptions.ResourceNotFoundException;
import com.java.sms.model.AppUser;
import com.java.sms.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/index").permitAll()
						.requestMatchers("/api/user").hasRole("USER").requestMatchers("/api/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
				.httpBasic();
		return http.build();
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
//		UserDetails user = User.builder().username("user").password(encoder.encode("user123")).roles("USER").build();
//
//		UserDetails admin = User.builder().username("admin").password(encoder.encode("admin123")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> {
			AppUser appUser = userRepository.findByUsername(username).orElseThrow(
					() -> new ResourceNotFoundException("Student Not Found with roll number: " + username));

			return User.builder().username(appUser.getUsername()).password(appUser.getPassword()) // Must be BCrypt-encoded
					.roles(appUser.getRole()) // e.g., "USER" or "ADMIN"
					.build();
		};

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
