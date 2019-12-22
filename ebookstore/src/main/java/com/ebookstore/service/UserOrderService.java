package com.ebookstore.service;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.Payment;
import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;

public interface UserOrderService {

	UserOrder createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment, String shippingMethod, 
			User user);
	
	UserOrder findById(int userOrderId);
}
