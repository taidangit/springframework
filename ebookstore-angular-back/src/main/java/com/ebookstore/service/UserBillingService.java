package com.ebookstore.service;

import com.ebookstore.domain.UserBilling;

public interface UserBillingService {
	
	void save(UserBilling userBilling);
	
	UserBilling findById(int userBillingId);
}
