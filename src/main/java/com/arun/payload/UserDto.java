package com.arun.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 3 ,message = "Name must be more than 3 character")
	private String name;
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	@Size(min = 3)
	private String password;
	
	@NotBlank
	private String about;


}
