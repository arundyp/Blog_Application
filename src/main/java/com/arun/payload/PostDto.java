package com.arun.payload;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private int pId;
	@NotEmpty
	@Size(min = 3,max = 20 ,message = "Title can not be blank or it should be more than 3 char")
	private String title;
	@NotEmpty
	@Size(min = 3,max = 200 ,message = "Content can not be blank or it should be more than 3 char")
	private String content;
	@NotEmpty
	private String image;
	private Date date;
	private UserDto user;
	private CategoryDto category;

}
