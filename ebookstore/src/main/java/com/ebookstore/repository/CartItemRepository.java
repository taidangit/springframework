package com.ebookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.UserOrder;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByUserOrder(UserOrder userOrder);
}
