package com.lcwd.store;

import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lcwd.store.respository.UserDao;

@SpringBootApplication
public class MyStoreApplication {

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(MyStoreApplication.class, args);
		// Test

	}

}
