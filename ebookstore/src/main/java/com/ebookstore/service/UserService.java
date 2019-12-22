package com.ebookstore.service;

import com.ebookstore.domain.PasswordResetToken;
import com.ebookstore.domain.User;

public interface UserService {

	PasswordResetToken getPasswordResetToken(String token);
	
	void createPasswordResetToken(User user, String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	void save(User user);
	
	User findById(int userId);
}
