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
import org.springframework.web.bind.annotation.RestController;
import com.arun.payload.ApiResponse;
import com.arun.payload.CategoryDto;
import com.arun.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	// create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryServices.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}

	// update category
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int id) {
		CategoryDto updateCategory = this.categoryServices.updateCategory(categoryDto, id);

		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.ACCEPTED);

	}

	// delete category
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletecategory(@PathVariable int id) {
		this.categoryServices.deleteCategory(id);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Record deleted Sucessfully", true),
				HttpStatus.ACCEPTED);

	}

	// get by category id
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int catId) {

		CategoryDto findById = this.categoryServices.findById(catId);
		return new ResponseEntity<CategoryDto>(findById, HttpStatus.OK);

	}
	
	//get allCategory
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allcategory = this.categoryServices.getAllcategory();
		return new ResponseEntity<>(allcategory,HttpStatus.OK);
		
	}
	
	
	
	

}
