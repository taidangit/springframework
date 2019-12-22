package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	User findByEmail(String email);
}
