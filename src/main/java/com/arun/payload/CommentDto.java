package com.arun.payload;

import com.arun.entity.Post;

import lombok.Data;
@Data
public class CommentDto {
	
	private int id;
	private String comment;
	private Post post;

}
