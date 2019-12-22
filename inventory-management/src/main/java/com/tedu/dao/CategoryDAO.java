package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;

public interface CategoryDAO<E> extends BaseDAO<E> {
	
	List<E> searchCategories(String property, String categoryName, Paging paging);
}
