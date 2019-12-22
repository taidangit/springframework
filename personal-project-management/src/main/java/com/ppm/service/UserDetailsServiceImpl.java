package com.ppm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppm.domain.User;
import com.ppm.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return user;
	}
	
	@Transactional
	public User loadUserByUserId(int userId) throws UsernameNotFoundException{
		User user = userRepository.findById(userId).get();
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return user;
	}

}
