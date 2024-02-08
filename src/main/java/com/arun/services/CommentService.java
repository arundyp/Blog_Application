package com.arun.services;

import com.arun.payload.CommentDto;

public interface CommentService {
	// create post
	CommentDto createComment(CommentDto commentDto, int pId);

	// delete post
	void deleteComment(int cId);

}
