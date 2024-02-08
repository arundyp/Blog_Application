package com.arun.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.arun.entity.User;
import com.arun.payload.UserDto;

public interface UserServices {
	
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto user, int id);

	void deleteUser(int id);

	UserDto getById(int id);

	List<UserDto> getAllUser();
	
	//get user except password
	List<User> getSingleDataofEmp();
	

}
