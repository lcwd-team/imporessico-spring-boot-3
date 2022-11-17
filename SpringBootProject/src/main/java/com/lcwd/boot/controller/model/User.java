package com.lcwd.boot.controller.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private String city;
	private String profession;
	private String exp;
	private List<String> courses = new ArrayList<>();
	private boolean active;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	
	

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", city=" + city + ", profession=" + profession + ", exp=" + exp + ", courses="
				+ courses + ", active=" + active + "]";
	}

	


}
