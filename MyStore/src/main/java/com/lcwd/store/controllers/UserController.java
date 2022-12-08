package com.lcwd.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.JwtRequest;
import com.lcwd.store.dtos.JwtResponse;
import com.lcwd.store.dtos.UserDto;
import com.lcwd.store.excetions.BadRequestException;
import com.lcwd.store.services.UserService;

import security.JwtTokenHelper;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper helper;

	// create
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		UserDto userDto2 = userService.addUser(userDto);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);
	}

	// get single.

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
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
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		userService.deletUser(userId);
		return ResponseEntity.ok(ApiResponse.builder().message("User is deleted").success(true).build());
	}

	// update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
		UserDto userDto2 = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.OK);
	}

	// search
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
		List<UserDto> searchUsers = userService.searchUser(keywords);
		return ResponseEntity.ok(searchUsers);
	}

	// login api
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = helper.generateToken(userDetails);
		JwtResponse build = JwtResponse.builder().jwtToken(token).userDetails(userDetails).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(build);

	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {

			this.manager.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
			logger.info("Invalid Username password !!");

			BadRequestException exception = new BadRequestException("Invalid Username and Password !!");
			throw exception;
		}
	}

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
