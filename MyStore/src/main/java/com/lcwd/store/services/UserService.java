package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.UserDto;

public interface UserService {
	
	//add user
	UserDto addUser(UserDto userDto);
	//update
	UserDto updateUser(UserDto user,int userId);
	//get single
	UserDto getUser(int userId);
	//get All
	List<UserDto> getAll();
	//delete user
	void deletUser(int userId);
	List<UserDto> searchUser(String keywords);
	
}
