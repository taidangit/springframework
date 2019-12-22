package com.ebookstore.service;

import org.springframework.stereotype.Service;

import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.UserShipping;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Override
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressCity(userShipping.getCity());
		shippingAddress.setShippingAddressCountry(userShipping.getCountry());
		shippingAddress.setShippingAddressName(userShipping.getName());
		shippingAddress.setShippingAddressState(userShipping.getState());
		shippingAddress.setShippingAddressStreet(userShipping.getStreet());
		shippingAddress.setShippingAddressZipcode(userShipping.getZipCode());
		
		return shippingAddress;
	}

	
}
