package com.lcwd.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.JWTAuthenticationEntryPoint;
import security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private String[] PUBLIC_URLS= {
			"/swagger-ui/**",
			"/webjars/**",
			"/swagger-resources/**",
			"/v3/api-docs",
			"/v2/api-docs"
		
			
	};
	
	private String[] adminURL= {
			"/users/**",			
			"/products/**",
			"/categories/**"
	};
	
  
//	@Autowired
//	public void configGlobal(AuthenticationManagerBuilder manager) throws Exception {
//
//		manager.inMemoryAuthentication().withUser("jatin").password(passwordEncoder.encode("123"))
//				.authorities("ROLE_ADMIN").and().withUser("denis").password(passwordEncoder.encode("123"))
//				.authorities("ROLE_USER");
//
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.DELETE,adminURL).hasRole("ADMIN")
		.antMatchers(PUBLIC_URLS)
		.permitAll()
		.antMatchers(HttpMethod.POST,"/users/**")	
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		//authentication provider		
		http.authenticationProvider(doAuthenticationProvider());
		

//		http
//		.authorizeHttpRequests()
////		.antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ROLE_USER")	
////		.antMatchers(HttpMethod.POST,"/users").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();

		return http.build();

	}
	
	@Bean
	public DaoAuthenticationProvider doAuthenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
