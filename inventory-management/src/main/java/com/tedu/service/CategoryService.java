package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.CategoryDAO;
import com.tedu.entity.Category;
import com.tedu.entity.Paging;

@Service
public class CategoryService {

	private static final Logger log = Logger.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	
	public void saveCategory(Category category) {
		log.info("Insert Category "+category.getName());
		
		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}
	
	public void updateCategory(Category category) {
		log.info("Update Category "+category.getName());
		
		Category category2 = categoryDAO.findById(Category.class, category.getCategoryId());
		
		category.setActiveFlag(1);
		category.setCreateDate(category2.getCreateDate());
		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}
	
	public void deleteCategory(Category category) {
		log.info("Delete Category "+category.getName());
		category.setActiveFlag(0);
		categoryDAO.update(category);
	}
	
	public List<Category> findCategory(String property, Object value) {
		log.info("Find category by property= "+property+", value= "+value);
		return categoryDAO.findByProperty(property, value);
	}
	
	public List<Category> getCategories(Paging paging) {
		log.info("Get all category");
		return categoryDAO.findAll(paging);
	}
	
	public Category findCategoryById(int id) {
		log.info("Find category by id= "+id);
		return categoryDAO.findById(Category.class, id);
	}
	
	public void deleteCategory(String property, int id) {
		categoryDAO.delete(property, id);
	}
	
	public List<Category> searchCategories(String property, String categoryName, Paging paging) {
		return categoryDAO.searchCategories(property, categoryName, paging);
	}
}

