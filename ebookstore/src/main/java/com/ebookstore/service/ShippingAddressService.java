package com.ebookstore.service;

import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.UserShipping;

public interface ShippingAddressService {

	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
