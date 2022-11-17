package com.learn.impressico.logic;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Jatin {
	Sort sort;
	String name;
	int age;
	List<String> courses;
	Set<String> friends;
	Map<String, Integer> marks;
	
	
	

	public Jatin() {
		System.out.println("creating jatin object using default constructor");
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
		System.out.println("setting sort value");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void sortSomething() {
		sort.sortData();
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public Set<String> getFriends() {
		return friends;
	}

	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}

	public Map<String, Integer> getMarks() {
		return marks;
	}

	public void setMarks(Map<String, Integer> marks) {
		this.marks = marks;
	}
	
	

}
