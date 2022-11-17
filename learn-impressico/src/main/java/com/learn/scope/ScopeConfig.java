package com.learn.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.learn.scope")
public class ScopeConfig {

	// i am going to create two beans of same class with the help of methods and
	// @Bean

	@Bean("pepsi1")
	public Pepsi bean1() {
		Pepsi pepsi = new Pepsi();
		pepsi.setBottleNumber("1");
		return pepsi;

	}

	@Bean("pepsi2")
	public Pepsi bean2() {
		Pepsi pepsi = new Pepsi();
		pepsi.setBottleNumber("2");
		return pepsi;
	}

}
