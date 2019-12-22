package com.ebookstore.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.Book;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.Payment;
import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;
import com.ebookstore.repository.BookRepository;
import com.ebookstore.repository.CartItemRepository;
import com.ebookstore.repository.UserOrderRepository;

@Service
public class UserOrderServiceImpl implements UserOrderService {
	
	@Autowired
	private UserOrderRepository userOrderRepository;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public UserOrder createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user) {
		UserOrder order = new UserOrder();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		order.setUser(user);
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		for(CartItem cartItem: cartItems) {
			cartItem.setUserOrder(order);
			
			Book book = cartItem.getBook();
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
		}
		
		
		order.setOrderDate(LocalDate.now());
		if(shippingMethod.equals("groundShipping")) {
			order.setShippingDate(LocalDate.now().plusDays(5));
		} else {
			order.setShippingDate(LocalDate.now().plusDays(3));
		}
		order.setOrderTotal(shoppingCart.getGrandTotal());
		userOrderRepository.save(order);
		
		return order;
	}

	@Override
	public UserOrder findById(int userOrderId) {
		return userOrderRepository.findById(userOrderId).get();
	}
	
	
	
}
