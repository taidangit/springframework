package com.dangphattai.dao;

import com.dangphattai.entity.User;

public interface UserDAO {
	
	boolean checkUser(String username, String password);
	
	int getRoleId(String username, String password);
	
	boolean checkUserByUsername(String username);
	
	User saveUser(User user);
	
}
