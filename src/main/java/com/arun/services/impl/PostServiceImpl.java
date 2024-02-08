package com.arun.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arun.entity.Category;
import com.arun.entity.Post;
import com.arun.entity.User;
import com.arun.exception.ResourceNotFoundException;
import com.arun.payload.PostDto;
import com.arun.payload.PostResponse;
import com.arun.repository.CategoryRepo;
import com.arun.repository.PostRepo;
import com.arun.repository.UserRepo;
import com.arun.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		Post post = this.mapper.map(postDto, Post.class);
		post.setCategory(cat);
		post.setUser(user);
		post.setDate(new Date());
		Post save = this.postRepo.save(post);
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postid", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		Post save = this.postRepo.save(post);
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postid", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostDto getSinglePost(int postid) {
		Post post = this.postRepo.findById(postid)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postid));
		PostDto postDto = this.mapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse  getAllPost(int pageNumber, int pageSize,String sortBy) {

		Sort sort = Sort.by(sortBy);
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> PagePost = this.postRepo.findAll(pageRequest);
		List<Post> content = PagePost.getContent();

		List<PostDto> collect = content.stream().map((data) -> this.mapper.map(data, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(pageRequest.getPageNumber());
		postResponse.setPageSize(pageRequest.getPageSize());
		postResponse.setTotalElements(PagePost.getTotalElements());
		postResponse.setTotalPage(PagePost.getTotalPages());
		postResponse.setLastPage(PagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		List<Post> findByUser = this.postRepo.findByUser(user);
		List<PostDto> collect = findByUser.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> getPostsByCategory(int catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));

		List<Post> findByCategory = this.postRepo.findByCategory(category);

		List<PostDto> collect = findByCategory.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> conating = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> collect = conating.stream().map((post)->this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}

}
