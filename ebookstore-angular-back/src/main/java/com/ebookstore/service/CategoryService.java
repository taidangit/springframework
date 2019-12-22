package com.ebookstore.service;

import java.util.List;

import com.ebookstore.domain.Category;

public interface CategoryService {

	List<Category> findAll();
	Category findById(int categoryId);
}
