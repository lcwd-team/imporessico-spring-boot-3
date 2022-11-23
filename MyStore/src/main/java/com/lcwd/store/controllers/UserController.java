package com.lcwd.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.UserDto;
import com.lcwd.store.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// create
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDto2 = userService.addUser(userDto);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);
	}

	// get single.

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
		UserDto user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	// get all

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getAll());
	}

	// delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
		userService.deletUser(userId);
		return ResponseEntity.ok(ApiResponse.builder().message("User is deleted").success(true).build());
	}

	// update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId) {
		UserDto userDto2 = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.OK);
	}

	// search

	// get single.

	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
		List<UserDto> user = userService.searchUser(keywords);
		return ResponseEntity.ok(user);
	}

	// // method for handling exception
	// @ExceptionHandler(ResourceNotFountException.class)
	// public ResponseEntity<ApiResponse>
	// handleRunTimeException(ResourceNotFountException e){
	//
	// logger.info("Runtime Exception Generated : {} ",e.getMessage());
	// return new
	// ResponseEntity<ApiResponse>(ApiResponse.builder().message(e.getMessage()).success(false).build(),
	// HttpStatus.NOT_FOUND);
	// }

}
