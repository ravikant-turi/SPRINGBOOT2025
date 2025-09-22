package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChainSecurity(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/index").permitAll()
						.requestMatchers("/api/user").hasRole("USER").requestMatchers("/api/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
				.httpBasic();
		return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("user123")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//	    return username -> {
//	        AppUser appUser = userRepository.findByUsername(username)
//	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//	        return User.builder()
//	            .username(appUser.getUsername())
//	            .password(appUser.getPassword()) // Must be encoded
//	            .roles(appUser.getRole())
//	            .build();
//	    };
//	}

}
