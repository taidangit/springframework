package com.ebookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.PasswordResetToken;
import com.ebookstore.domain.User;
import com.ebookstore.repository.PasswordResetTokenRepository;
import com.ebookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetToken(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findById(int userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
