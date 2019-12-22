package com.ebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.Category;
import com.ebookstore.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int categoryId) {
		Optional<Category> result = categoryRepository.findById(categoryId);
		Category category;
		
		if(result.isPresent()) {
			category = result.get();
		} else {
			category = null;
		}
		
		return category;
	}
}
