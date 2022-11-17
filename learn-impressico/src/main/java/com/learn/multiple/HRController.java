package com.learn.multiple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HRController {

	@Autowired
	@Qualifier("diwaliService")
	private UserService userService;

	public void updateSalary() {

		this.userService.dowork();
	}

}
