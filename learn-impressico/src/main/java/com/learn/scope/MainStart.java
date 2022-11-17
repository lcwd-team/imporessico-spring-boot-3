package com.learn.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainStart {
	public static void main(String[] args) {
		System.out.println("Program started: ");

		ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		Student student = context.getBean("student", Student.class);
		System.out.println(student);

		Student student1 = context.getBean("student", Student.class);
		System.out.println(student1);

		Student student2 = context.getBean("student", Student.class);
		System.out.println(student2);

		Pepsi pepsi = student.getPepsi();
//		
//		Pepsi pepsi = context.getBean("pepsi2",Pepsi.class);
//		System.out.println(pepsi);
//		
		System.out.println(pepsi);

	}
}
