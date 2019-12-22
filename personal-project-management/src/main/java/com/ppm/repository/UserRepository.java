package com.ppm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppm.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

}
