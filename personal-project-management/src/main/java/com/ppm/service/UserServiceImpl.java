package com.ppm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ppm.domain.User;
import com.ppm.exception.PasswordException;
import com.ppm.exception.UsernameException;
import com.ppm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;

	@Override
	public void save(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setConfirmPassword("");
			userRepository.save(user);
		} catch(Exception e) {
			throw new UsernameException("Username "+user.getUsername()+" already exists.");
		}
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(int userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public void update(User user) {
		User currentUser = userService.findById(user.getUserId());
		
		if (userService.findByUsername(user.getUsername()) != null) {
			if(userService.findByUsername(user.getUsername()).getUserId() != currentUser.getUserId()) {
				throw new UsernameException("Username already exist.");
			}
		}
		
		if(bCryptPasswordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
		} else {
			throw new PasswordException("Incorrect password.");
		}
		
		userRepository.save(user);
	}
}
