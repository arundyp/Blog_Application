package com.arun.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.entity.Category;
import com.arun.exception.ResourceNotFoundException;
import com.arun.payload.CategoryDto;
import com.arun.repository.CategoryRepo;
import com.arun.services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {

		Category cat = this.mapper.map(catDto, Category.class);
		this.categoryRepo.save(cat);
		CategoryDto categoryDto = this.mapper.map(cat, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, int catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Content", "id", catId));
		category.setCategoryTitle(catDto.getCategoryTitle());
		category.setCategoryContent(catDto.getCategoryContent());
		category.setAddedDate(catDto.getAddedDate());
		this.categoryRepo.save(category);

		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);

		return categoryDto;
	}

	@Override
	public void deleteCategory(int catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "catid", catId));

		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDto findById(int catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "catid", catId));
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllcategory() {
		List<Category> findAll = this.categoryRepo.findAll();
		List<CategoryDto> collect = findAll.stream().map((cat)->this.mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return collect;
	}

}
