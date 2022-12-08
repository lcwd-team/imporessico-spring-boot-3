package com.lcwd.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lcwd.store.respository.UserDao;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lcwd.store","security"})
public class MyStoreApplication implements CommandLineRunner {

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(MyStoreApplication.class, args);
		// Test
	
		

	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(passwordEncoder.encode("123"));
		
	}

}
