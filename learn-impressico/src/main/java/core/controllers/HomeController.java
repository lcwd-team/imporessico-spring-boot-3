package core.controllers;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component("home")
public class HomeController {

//	FileInputStream file;

	@PostConstruct
	public void initialize() {
//		file=new FileInputStream("test.java");
		System.out.println("open connection");
	}

	public void controlHome() {
		System.out.println("Controlling home");
		System.out.println("using connection details");
	}

	@PreDestroy
	public void destroy() {
//		file.close();
		System.out.println("close connection");
	}
}
