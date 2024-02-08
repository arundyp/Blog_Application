package com.arun.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arun.entity.Role;
import com.arun.entity.User;
import com.arun.exception.ResourceNotFoundException;
import com.arun.payload.AppConstants;
import com.arun.payload.UserDto;
import com.arun.repository.RoleRepo;
import com.arun.repository.UserRepo;
import com.arun.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepo repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);
		User save = this.repository.save(user);
		return this.userToDto(save);

	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {

		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User save = this.repository.save(user);

		UserDto userToDto = this.userToDto(user);

		return userToDto;
	}

	@Override
	public void deleteUser(int id) {
		User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		this.repository.delete(user);

	}

	@Override
	public UserDto getById(int id) {
		User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAll = this.repository.findAll();
		List<UserDto> userDto = findAll.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	public User dtoToUser(UserDto userDto) {

		User user = this.mapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;

	}

	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		// UserDto userDto = this.mapper.map(user, UserDto.class);
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		// userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRole().add(role);

		User newUser = this.repository.save(user);

		return this.mapper.map(newUser, UserDto.class);
	}

	@Override
	public List<User> getSingleDataofEmp() {
		List<User> users = this.repository.getUsers();
		return users;
	}

}
