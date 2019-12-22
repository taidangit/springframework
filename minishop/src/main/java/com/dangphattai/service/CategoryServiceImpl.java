package com.dangphattai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.CategoryDAO;
import com.dangphattai.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> getCategories() {
		return categoryDAO.getCategories();
	}

	@Override
	public Category getCategory(int categoryId) {
		return categoryDAO.getCategory(categoryId);
	}

}
