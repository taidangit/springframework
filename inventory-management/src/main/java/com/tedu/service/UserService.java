package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.UserDAO;
import com.tedu.entity.Paging;
import com.tedu.entity.User;
import com.tedu.util.HashingPassword;

@Service
public class UserService {
	
	private final static Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDAO<User> userDAO;
	
	
	public List<User> getUsers(Paging paging) {
		log.info("Get all user");
		return userDAO.findAll(paging);
	}
	
	public List<User> findByProperty(String property, Object value) {
		log.info("Find user by property");
		
		return userDAO.findByProperty(property, value);
	}
	

	public User findUserById(int id) {
		log.info("Find user by id ");
		return userDAO.findById(User.class, id);
	}

	public void saveUser(User user) {
		user.setActiveFlag(1);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setPassword(HashingPassword.encrypt(user.getPassword()));
		
		userDAO.save(user);
	
	}

	public void updateUser(User user) {
		
		User user2 = findUserById(user.getUserId());
		
		user.setActiveFlag(1);
		user.setCreateDate(user2.getCreateDate());
		user.setUpdateDate(new Date());
		user.setPassword(HashingPassword.encrypt(user.getPassword()));
		userDAO.update(user);
	}

	public void deleteUser(String property, int id) {
		userDAO.delete(property, id);
	}
	
	public List<User> searchUser(String property, String name, Paging paging) {
		return userDAO.searchUser(property, name, paging);
	}
}
