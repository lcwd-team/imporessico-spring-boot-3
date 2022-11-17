package com.lcwd.store.dtos;

import java.util.Date;

import com.lcwd.store.entities.User;

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
public class UserDto {

	private int id;

	private String name;

	private String email;

	private String password;

	private String about;

	private String gender;

	private Date dob;

}
