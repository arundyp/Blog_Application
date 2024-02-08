package com.arun.services;

import java.util.List;

import com.arun.payload.PostDto;
import com.arun.payload.PostResponse;

public interface PostService {
	// create
	PostDto createPost(PostDto postDto, int userId, int categoryId);

	// update
	PostDto updatePost(PostDto postDto, int postId);

	// delete post
	void deletePost(int postId);

	// get post
	PostDto getSinglePost(int postid);

	// getAll Post
	PostResponse getAllPost(int pageNumber, int pageSize,String sortBy);

	// get All post by user

	List<PostDto> getPostByUser(int userId);

	// get All post by category

	List<PostDto> getPostsByCategory(int catId);
	
	List<PostDto>searchPost(String keyword);

}
