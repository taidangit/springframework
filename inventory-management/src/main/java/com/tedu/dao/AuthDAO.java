package com.tedu.dao;

import java.util.List;

public interface AuthDAO<E> extends BaseDAO<E> {

	public List<E> findAuth(int roleId, int menuId);
}
