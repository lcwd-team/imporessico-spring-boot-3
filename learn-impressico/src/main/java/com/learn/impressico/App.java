package com.learn.impressico;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learn.impressico.logic.Akash;
import com.learn.impressico.logic.BubbleSort;
import com.learn.impressico.logic.Jatin;
import com.learn.impressico.logic.SelectionSort;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// wants jatin
		ApplicationContext context = new ClassPathXmlApplicationContext("com/learn/impressico/config.xml","com/learn/impressico/akashConfig.xml");
		System.out.println(context);
		Jatin jatin = context.getBean(Jatin.class);
		jatin.sortSomething();
//		BubbleSort bean = context.getBean(BubbleSort.class);
//		SelectionSort bean2 = context.getBean(SelectionSort.class);
//		System.out.println(bean);
//		System.out.println(bean2);

		List<String> courses = jatin.getCourses();
		Set<String> friends = jatin.getFriends();
		Map<String, Integer> marks = jatin.getMarks();

		System.out.println(courses);
		System.out.println(friends);
		System.out.println(marks);
		
		Akash akash = context.getBean(Akash.class);
		System.out.println(akash.getA());
		

	}
}
