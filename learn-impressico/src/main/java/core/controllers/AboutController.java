package core.controllers;

import org.springframework.stereotype.Component;

@Component("about")
public class AboutController {
	public void controlAbout() {
		System.out.println("controlling about");
	}
}
