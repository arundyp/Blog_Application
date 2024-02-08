package com.arun.controller;

import java.util.List;

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

import com.arun.entity.User;
import com.arun.payload.ApiResponse;
import com.arun.payload.UserDto;
import com.arun.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServices userServices;

	// create
	@PostMapping("/")
	public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto userDto) {

		UserDto user = this.userServices.createUser(userDto);

		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

	// update

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int id) {
		UserDto user = this.userServices.updateUser(userDto, id);
		System.out.println(user);
		return ResponseEntity.ok(user);

	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deteleUser(@PathVariable int id) {
		this.userServices.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("Record deleted sucessfully", true), HttpStatus.OK);

	}

	@GetMapping("/arun")
	public ResponseEntity<List<UserDto>> getAllUser() {

		List<UserDto> allUser = this.userServices.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserByid(@PathVariable int id) {
		UserDto byId = this.userServices.getById(id);
		System.out.println("byId" + byId);

		return new ResponseEntity<>(byId, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getSingleDataofEmps() {
		 List<User> singleDataofEmp = this.userServices.getSingleDataofEmp();

		return new ResponseEntity<>(singleDataofEmp, HttpStatus.ACCEPTED);
	}

}
