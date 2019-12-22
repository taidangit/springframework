package com.tedu.dao;

import java.io.Serializable;
import java.util.List;

import com.tedu.entity.Paging;

public interface BaseDAO<E> {

	List<E> findAll(Paging paging);
	
	E findById(Class<E> e, Serializable id);
	
	List<E> findByProperty(String property, Object value);
	
	void save(E instance);
	
	void update(E instance);
	
	void delete(String property, int id);
}
