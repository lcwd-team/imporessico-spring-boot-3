package com.learn.multiple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("holiService")

public class UpdateHoliBonus implements UserService {

	public void dowork() {

		System.out.println("Updating holi bonus 10000000000");

	}

}
