package com.dangphattai.service;

import com.dangphattai.entity.User;

public interface UserService {

    boolean checkUser(String username, String password);
	
	int getRoleId(String username, String password);
	
	boolean checkUserByUsername(String username);
	
	User saveUser(User user);
}
