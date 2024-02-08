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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.payload.ApiResponse;
import com.arun.payload.PostDto;
import com.arun.payload.PostResponse;
import com.arun.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto, @PathVariable int userId,
			@PathVariable int catId) {

		PostDto createPost = this.postService.createPost(dto, userId, catId);

		return new ResponseEntity<>(createPost, HttpStatus.CREATED);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostOfUser(@PathVariable int userId) {

		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<>(postByUser, HttpStatus.OK);

	}

	@GetMapping("/post/category/{catId}")
	public ResponseEntity<List<PostDto>> getPostOfCategory(@PathVariable int catId) {

		List<PostDto> postByCategory = this.postService.getPostsByCategory(catId);
		return new ResponseEntity<>(postByCategory, HttpStatus.OK);

	}

	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam (value = "pageNumber", defaultValue="0",required=false) int pageNumber ,
			@RequestParam (value = "pageSize", defaultValue="10",required=false) int pageSize,
			@RequestParam (value = "sortBy", defaultValue="pId",required=false) String sortBy
			
			
			) {
		  PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize, sortBy);
		return new ResponseEntity<>(allPost, HttpStatus.OK);

	}

	@DeleteMapping("/post/{pId}")
	public ResponseEntity<ApiResponse> deletePosts(@PathVariable int pId) {

		this.postService.deletePost(pId);
		return new ResponseEntity<>(new ApiResponse("Record deleted sucessfully...", true), HttpStatus.OK);

	}
	@GetMapping("/post/{pId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int pId){
		PostDto post = this.postService.getSinglePost(pId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	
	@PutMapping("/post/{pId}")
	public ResponseEntity<PostDto> updatePosts(@Valid @RequestBody PostDto postDto, @PathVariable int pId){
		PostDto updatePost = this.postService.updatePost(postDto, pId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword){
		
		 List<PostDto> post = this.postService.searchPost(keyword);
		return new ResponseEntity<>(post,HttpStatus.OK);
		
	}

}
