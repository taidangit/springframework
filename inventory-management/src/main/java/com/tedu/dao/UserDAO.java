package com.tedu.dao;

import java.util.List;

import com.tedu.entity.Paging;
import com.tedu.entity.User;

public interface UserDAO<E> extends BaseDAO<E> {

	List<User> searchUser(String property, String name, Paging paging);
}
