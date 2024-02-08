package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
