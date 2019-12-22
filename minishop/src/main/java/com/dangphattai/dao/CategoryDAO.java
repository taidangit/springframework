package com.dangphattai.dao;

import java.util.List;

import com.dangphattai.entity.Category;

public interface CategoryDAO {

	List<Category> getCategories();
	
	Category getCategory(int categoryId);
}
