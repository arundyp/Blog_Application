package com.arun.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.entity.Comment;
import com.arun.entity.Post;
import com.arun.exception.ResourceNotFoundException;
import com.arun.payload.CommentDto;
import com.arun.repository.CommentRepo;
import com.arun.repository.PostRepo;
import com.arun.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int pId) {
		Post post = this.postRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException("post", "postId", pId));
		
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment save = this.commentRepo.save(comment);
		return this.mapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(int cId) {
		// TODO Auto-generated method stub

	}

}
