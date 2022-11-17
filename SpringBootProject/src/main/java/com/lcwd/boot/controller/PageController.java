package com.lcwd.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcwd.boot.controller.model.User;

@Controller
public class PageController {

	@RequestMapping("/about")
	public String aboutPage() {
		// name of the page
		System.out.println("this is about page");
		return "about";
	}

	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("this is services page");
		return "services";
	}
	
	@PostMapping("/page/user")
	@ResponseBody
	public ResponseEntity<User> user() {
		
		return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
		
//		return ResponseEntity.ok(new User());
		//return ResponseEntity.status(HttpStatus.ACCEPTED).body(new User());
	}


}
