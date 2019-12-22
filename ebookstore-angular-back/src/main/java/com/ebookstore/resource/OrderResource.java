package com.ebookstore.resource;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.Payment;
import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;
import com.ebookstore.service.CartItemService;
import com.ebookstore.service.ShoppingCartService;
import com.ebookstore.service.UserOrderService;
import com.ebookstore.service.UserService;
import com.ebookstore.utility.MailConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/order")
public class OrderResource {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@PostMapping("/{username}")
	public ResponseEntity<UserOrder> order(@PathVariable String username, @RequestBody HashMap<String, Object> mapper) {
		ObjectMapper objectMapper = new ObjectMapper();
		ShippingAddress shippingAddress = objectMapper.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
		BillingAddress billingAddress = objectMapper.convertValue(mapper.get("billingAddress"), BillingAddress.class);
		Payment payment = objectMapper.convertValue(mapper.get("payment"), Payment.class);
		
		String shippingMethod = (String) mapper.get("shippingMethod");
		
		User user = userService.findByUsername(username);
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		UserOrder userOrder = userOrderService.createOrder(
				shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);
		
		List<CartItem> cartItems = cartItemService.findByUserOrder(userOrder);
		userOrder.setCartItems(cartItems);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, userOrder, Locale.ENGLISH));
		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		return new ResponseEntity<UserOrder>(userOrder, HttpStatus.OK);
		
	}
	
	@GetMapping("/list/{username}")
	public ResponseEntity<List<UserOrder>> getOrdersList(@PathVariable String username) {
		User user = userService.findByUsername(username);
		List<UserOrder> orders = user.getUserOrders();
		
		return new ResponseEntity<List<UserOrder>>(orders, HttpStatus.OK);
	}

}
