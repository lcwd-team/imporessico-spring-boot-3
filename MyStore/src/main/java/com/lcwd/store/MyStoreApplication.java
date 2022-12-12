package com.lcwd.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lcwd.store.entities.Role;
import com.lcwd.store.respository.RoleRepo;
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

	@Autowired
	private RoleRepo repo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(passwordEncoder.encode("123"));
		
	
		try {
			
			Role role1 = Role.builder().roleId("awetafwedrgveswaebwht").roleName("ROLE_ADMIN").build();
			Role role2 = Role.builder().roleId("setvy5tbehgfcwtwevv").roleName("ROLE_NORMAL").build();
			repo.save(role1);
			repo.save(role2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
