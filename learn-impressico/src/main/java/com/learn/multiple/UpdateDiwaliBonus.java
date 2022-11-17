package com.learn.multiple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("diwaliService")

public class UpdateDiwaliBonus implements UserService {

	public void dowork() {

		System.out.println("updating 50000 salary to every employee");

	}

}
