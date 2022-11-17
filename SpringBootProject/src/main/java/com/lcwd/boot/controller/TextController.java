
package com.lcwd.boot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.boot.controller.model.Student;

@RestController
public class TextController {

	@RequestMapping("/contact")
	public String contactPage() {
		return "We are working in imporessico for api development";
	}

	@RequestMapping("/projects")
	public String projectText() {
		return "This is project txt";
	}

	@RequestMapping("/friends")
	public List<String> friendsList() {
		List<String> friends = Arrays.asList("Sahil", "Jatin", "Khushi", "Trainer2", "Kushagra", "Akash");
		return friends;
	}

	@RequestMapping("/fees")
	public Map<String, String> courseFees() {
		Map<String, String> fees = new HashMap<>();
		fees.put("java", "4000");
		fees.put("Spring boot", "5000");
		fees.put("Django", "6000");

		return fees;
	}

	@RequestMapping("/student")
	public Student getStudent() {	
		Student student = new Student();
		student.setName("Sahil");
		student.setPhone("123452315421");
		student.setRollNumber(141234);
		student.setCollege("IIT Kanpur");
		// creating subject
		List<String> subjects = Arrays.asList("Math", "Chemistry", "Biology", "Physics", "Computer Science");
		List<String> friends = Arrays.asList("Akash", "Manish", "Rani");
		student.setFriends(friends);
		student.setSubjects(subjects);
		System.out.println("this is testing");
		return student;
	}
}
