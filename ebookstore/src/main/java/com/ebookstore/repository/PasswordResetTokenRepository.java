package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.PasswordResetToken;
import com.ebookstore.domain.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByUser(User user);
}
