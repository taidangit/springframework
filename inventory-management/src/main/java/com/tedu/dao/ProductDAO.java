package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;

public interface ProductDAO<E> extends BaseDAO<E> {

	List<E> searchProducts(String property, String productName, Paging paging);
}
