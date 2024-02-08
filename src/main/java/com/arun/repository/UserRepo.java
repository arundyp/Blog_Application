package com.arun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arun.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User>findByEmail(String email);
	//select name,email from user u where u.id=302;
	//select id,email from User u where u.id =:id
	
	/*
	 * 
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title); 
	 * 
	 */
	
	//@Query( "select name,email from user u where u.id=302;")
	@Query("SELECT u FROM User u")
	List<User> getUsers();

}
