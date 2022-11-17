package com.lcwd.store.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	private String gender;
	
	private Date dob;
	
	
	
}
