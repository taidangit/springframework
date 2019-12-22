package com.ebookstore.service;

import com.ebookstore.domain.ShoppingCart;

public interface ShoppingCartService {

	void updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
	
	ShoppingCart findById(int shoppingCartId);

}
