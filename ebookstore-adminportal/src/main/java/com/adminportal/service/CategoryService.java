package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Category;

public interface CategoryService {

	List<Category> findAll();
	Category findById(int categoryId);
}
