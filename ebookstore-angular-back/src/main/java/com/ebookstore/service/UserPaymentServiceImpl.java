package com.ebookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserPayment;
import com.ebookstore.repository.UserPaymentRepository;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Override
	public UserPayment findById(int userPaymentId) {
		return userPaymentRepository.findById(userPaymentId).get();
	}

	@Override
	public void deleteById(int userPaymentId) {
		userPaymentRepository.deleteById(userPaymentId);
	}

	@Override
	public void setUserDefaultPayment(int userPaymentId, User user) {
		List<UserPayment> userPayments = user.getUserPayments();
		
		for(UserPayment userPayment : userPayments) {
			if(userPayment.getUserPaymentId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
			
		}
		
//		UserPayment userPayment = userPaymentRepository.findById(userPaymentId).get();
//		userPayment.setDefaultPayment(userPayment.isDefaultPayment() == true ? false : true);
//		
//		userPaymentRepository.save(userPayment);
	}

	@Override
	public void save(UserPayment userPayment) {
		userPaymentRepository.save(userPayment);
		
	}
	
	

}
