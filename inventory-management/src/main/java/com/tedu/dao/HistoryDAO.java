package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;

public interface HistoryDAO<E> extends BaseDAO<E> {

	List<E> searchHistories(String property, int type, Paging paging);
}
