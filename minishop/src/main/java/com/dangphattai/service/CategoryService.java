package com.dangphattai.service;

import java.util.List;

import com.dangphattai.entity.Category;

public interface CategoryService {

	List<Category> getCategories();
	
	Category getCategory(int CategoryId);
}
