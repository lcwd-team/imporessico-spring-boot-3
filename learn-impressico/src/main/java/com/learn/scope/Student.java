package com.learn.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("student")
//defining the scope: singleton
//@Scope("prototype")
public class Student {

	
	@Autowired
	@Qualifier("pepsi1")
	private Pepsi pepsi;
	
	Student(){
		System.out.println("student object created:");
	}

	public Pepsi getPepsi() {
		return pepsi;
	}

	public void setPepsi(Pepsi pepsi) {
		this.pepsi = pepsi;
	}
	
	
}
