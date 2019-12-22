package com.ebookstore.service;

import java.util.List;

import com.ebookstore.domain.Book;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;

public interface CartItemService {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	void updateCartItem(CartItem cartItem);
	
	void addBookToCartItem(Book book, User user, int qty);
	
	CartItem findById(int cartItemId);
	
	void removeCartItem(int cartItemId);
	
	List<CartItem> findByUserOrder(UserOrder userOrder);
}
