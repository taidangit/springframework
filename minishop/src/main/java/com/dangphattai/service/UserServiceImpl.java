package com.dangphattai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.UserDAO;
import com.dangphattai.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean checkUser(String username, String password) {
		
		return userDAO.checkUser(username, password);
	}

	@Override
	public int getRoleId(String username, String password) {
		
		return userDAO.getRoleId(username, password);
	}

	@Override
	public boolean checkUserByUsername(String username) {
		
		return userDAO.checkUserByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		return userDAO.saveUser(user);
	}

}
