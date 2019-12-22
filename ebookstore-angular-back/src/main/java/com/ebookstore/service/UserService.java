package com.ebookstore.service;

import com.ebookstore.domain.User;

public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	void save(User user);
	
	User findById(int userId);
}
