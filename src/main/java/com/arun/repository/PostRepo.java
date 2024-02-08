package com.arun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.entity.Category;
import com.arun.entity.Post;
import com.arun.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post>findByTitleContaining(String keyword);

}
