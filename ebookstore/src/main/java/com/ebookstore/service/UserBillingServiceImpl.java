package com.ebookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.UserBilling;
import com.ebookstore.repository.UserBillingRepository;

@Service
public class UserBillingServiceImpl implements UserBillingService {

	@Autowired
	private UserBillingRepository userBillingRepository;

	@Override
	public void save(UserBilling userBilling) {
		userBillingRepository.save(userBilling);
		
	}

	@Override
	public UserBilling findById(int userBillingId) {
		return userBillingRepository.findById(userBillingId).get();
	}
	
	
}
