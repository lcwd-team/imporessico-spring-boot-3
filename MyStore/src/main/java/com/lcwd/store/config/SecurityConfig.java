package com.lcwd.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configGlobal(AuthenticationManagerBuilder manager) throws Exception {

		manager
		.inMemoryAuthentication()
		.withUser("jatin")
		.password(passwordEncoder.encode("123"))
		.authorities("ROLE_ADMIN")
		.and()
		.withUser("denis")
		.password(passwordEncoder.encode("123"))
		.authorities("ROLE_USER");

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.authorizeHttpRequests()
//		.antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ROLE_USER")	
//		.antMatchers(HttpMethod.POST,"/users").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();

		return http.build();

	}

}
