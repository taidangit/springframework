package com.ebookstore.service;

import org.springframework.stereotype.Service;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.UserBilling;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

	@Override
	public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
		billingAddress.setBillingAddressCity(userBilling.getCity());
		billingAddress.setBillingAddressCountry(userBilling.getCountry());
		billingAddress.setBillingAddressName(userBilling.getName());
		billingAddress.setBillingAddressState(userBilling.getState());
		billingAddress.setBillingAddressStreet(userBilling.getStreet());
		billingAddress.setBillingAddressZipcode(userBilling.getZipCode());
		
		return billingAddress;
	}

	
}
