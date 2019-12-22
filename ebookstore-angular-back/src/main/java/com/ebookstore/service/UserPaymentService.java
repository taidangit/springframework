package com.ebookstore.service;


import com.ebookstore.domain.User;
import com.ebookstore.domain.UserPayment;

public interface UserPaymentService {

	void save(UserPayment userPayment);
	
	UserPayment findById(int userPaymentId);
	
	void deleteById(int userPaymentId);
	
	void setUserDefaultPayment(int userPaymentId, User user);
}
