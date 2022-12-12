package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import com.lcwd.store.dtos.UserDto;
import com.lcwd.store.entities.Role;
import com.lcwd.store.entities.User;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.RoleRepo;
import com.lcwd.store.respository.UserRepository;
import com.lcwd.store.services.UserService;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper; 
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Value("${user.normal.role.id}")
    private String normalUserIdString;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setId(UUID.randomUUID().toString());
        
        //get the user as normal user:
        
        Role role = roleRepo.findById(normalUserIdString).get();
        
        user.getRoles().add(role);      
        
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = mapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        // get the old user:
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFountException("user with given id not found on server !!"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setDob(userDto.getDob());
        // update
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFountException("user with given id not found on server !!"));

        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deletUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFountException("user with given id not found on server !!"));
        userRepository.delete(user);

    }

    @Override
    public List<UserDto> searchUser(String keywords) {
        List<User> userList = userRepository.findByNameContaining(keywords);
        List<UserDto> userDtoList = userList.stream().map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtoList;
    }

}
