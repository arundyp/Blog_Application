package com.arun.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arun.payload.CategoryDto;


public interface CategoryServices {
	
	//create
	CategoryDto createCategory(CategoryDto catDto);
	
	//update
	CategoryDto updateCategory(CategoryDto catDto, int catId);
	
	
	//delete
	void deleteCategory(int catId);
	
	//find Category by Id	
	CategoryDto findById(int catId);
	
	
	//getAll  category
	List<CategoryDto> getAllcategory();
	
	

}
