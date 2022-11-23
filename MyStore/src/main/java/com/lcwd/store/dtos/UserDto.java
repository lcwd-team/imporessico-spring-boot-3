package com.lcwd.store.dtos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	private String id;

	@NotBlank(message = "Name is Required !!")
	@Size(min = 5, max = 15, message = "User name must be of Min=5 and Max=15 !!")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Invalid User Name !!")
	private String name;

	@Email(message = "Valid email is Required !!")
	private String email;

	@NotBlank(message = "Password is required !!")
	@Size(min = 5, max = 15, message = "Min=5 and Max=15 allowed !!")
	private String password;

	private String about;

	@NotBlank(message = "Gender is required")
	private String gender;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dob;

}
