package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;

public interface ProductInStockDAO<E> extends BaseDAO<E> {

	List<E> searchProductInStocks(String property, String productName, Paging paging);
}
