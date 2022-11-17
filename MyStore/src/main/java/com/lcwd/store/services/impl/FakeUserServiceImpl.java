package com.lcwd.store.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.UserDto;
import com.lcwd.store.entities.User;
import com.lcwd.store.services.UserService;

@Service
@Primary
public class FakeUserServiceImpl implements UserService {

	private List<User> users = new ArrayList<>();
	@Autowired
	private ModelMapper mapper;
	private Logger logger = LoggerFactory.getLogger(FakeUserServiceImpl.class);
	@Override
	public UserDto addUser(UserDto userDto) {
		// DTO->ENTITY
		User user = mapper.map(userDto, User.class);
		boolean result = users.add(user);
		logger.info("User is added: {}", result);
		// Entity -> DTO
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		List<User> updatedList = users.stream().map(user -> {
			if (user.getId() == userId) {
				// user:update
				user.setName(userDto.getName());
				user.setGender(userDto.getGender());
				user.setDob(userDto.getDob());
				user.setEmail(userDto.getEmail());
				user.setAbout(userDto.getAbout());
				user.setPassword(userDto.getPassword());
				return user;
			} else {
				return user;
			}

		}).collect(Collectors.toList());

		users = updatedList;
		userDto.setId(userId);
		return userDto;
	}

	@Override
	public UserDto getUser(int userId) {
		User user1 = users.stream().filter(user -> user.getId() == userId).findFirst()
				.orElseThrow(() -> new RuntimeException("User with given id not found"));
		return mapper.map(user1, UserDto.class);
	}

	@Override
	public List<UserDto> getAll() {

		List<UserDto> userDtos = users.stream().map(user -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deletUser(int userId) {

		List<User> newList = users.stream().filter(user -> user.getId() != userId).collect(Collectors.toList());
		users = newList;
	}

	@Override
	public List<UserDto> searchUser(String keywords) {

		List<User> list = users.stream().filter(user -> user.getName().contains(keywords)).collect(Collectors.toList());

		List<UserDto> userDtos = list.stream().map(user -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return userDtos;
	}

}
