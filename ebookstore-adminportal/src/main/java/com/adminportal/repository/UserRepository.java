package com.adminportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminportal.domain.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	User findByEmail(String email);
}
