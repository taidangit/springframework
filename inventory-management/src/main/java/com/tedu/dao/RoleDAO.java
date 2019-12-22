package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;

public interface RoleDAO<E> extends BaseDAO<E> {

	List<E> searchRole(String property, String roleName, Paging paging);
}
