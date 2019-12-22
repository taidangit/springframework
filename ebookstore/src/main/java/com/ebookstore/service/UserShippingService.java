package com.ebookstore.service;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserShipping;

public interface UserShippingService {

	void save(UserShipping userShipping);

	UserShipping findById(int userShippingId);
	
	void deleteById(int userShippingId);
	
	void setUserDefaultShipping(int userShippingId, User user);
}
