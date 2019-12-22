package com.ppm.service;

import com.ppm.domain.User;

public interface UserService {

	void save(User user);
	
	void update(User user);
	
	User findByUsername(String username);
	
	User findById(int userId);
}
