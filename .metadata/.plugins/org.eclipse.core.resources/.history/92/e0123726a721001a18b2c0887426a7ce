package com.ebookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserShipping;
import com.ebookstore.repository.UserShippingRepository;

@Service
public class UserShippingServiceImpl implements UserShippingService {
	
	@Autowired
	private UserShippingRepository userShippingRepository;

	@Override
	public void save(UserShipping userShipping) {
		userShippingRepository.save(userShipping);
	}

	@Override
	public UserShipping findById(int userShippingId) {
		return userShippingRepository.findById(userShippingId).get();
	}

	@Override
	public void deleteById(int userShippingId) {
		userShippingRepository.deleteById(userShippingId);
	}

	@Override
	public void setUserDefaultShipping(int userShippingId, User user) {
		List<UserShipping> userShippings = user.getUserShippings();
		
		for(UserShipping userShipping : userShippings) {
			if(userShipping.getUserShippingId() == userShippingId) {
				userShipping.setShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
			
		}
		
	}
	
	

}
