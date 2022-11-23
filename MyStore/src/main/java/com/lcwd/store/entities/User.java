package com.lcwd.store.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
// jpa
@Entity // important
@Table(name = "jpa_users") // optional
public class User {

	@Id // important
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private String id;

	@Column(name = "user_name", length = 100, nullable = false)
	private String name;

	@Column(name = "user_email", unique = true)
	private String email;

	private String password;

	private String about;

	private String gender;

	private Date dob;

	// public User() {
	// this.id = UUID.randomUUID().toString();
	// }

}
