package com.ebookstore.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.Payment;
import com.ebookstore.domain.ShippingAddress;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserBilling;
import com.ebookstore.domain.UserPayment;
import com.ebookstore.domain.UserShipping;
import com.ebookstore.service.BillingAddressService;
import com.ebookstore.service.CartItemService;
import com.ebookstore.service.PaymentService;
import com.ebookstore.service.ShippingAddressService;
import com.ebookstore.service.UserPaymentService;
import com.ebookstore.service.UserService;
import com.ebookstore.service.UserShippingService;
import com.ebookstore.utility.MessageConstants;
import com.ebookstore.utility.USConstants;

@Controller
public class CheckoutController {

	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillingAddress billingAddress = new BillingAddress();
	private Payment payment = new Payment();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private BillingAddressService billingAddressService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@Autowired
	private UserPaymentService userPaymentService;

	
	@GetMapping("/checkout/{id}")
	public String checkout(@PathVariable("id") int shoppingCartId,
			Principal principal, Model model, HttpSession session) {
		
		User user = userService.findByUsername(principal.getName());
		
		if(shoppingCartId != user.getShoppingCart().getShoppingCartId()) {
			return "badRequestPage";
		}
		
		if(session.getAttribute(MessageConstants.MISSING_REQUIRED_FIELD) != null) {
			model.addAttribute(MessageConstants.MISSING_REQUIRED_FIELD, 
					session.getAttribute(MessageConstants.MISSING_REQUIRED_FIELD));
			session.removeAttribute(MessageConstants.MISSING_REQUIRED_FIELD);
		}
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(user.getShoppingCart());
		if(cartItems.size() == 0) {
			return "redirect:/shoppingCart/cart";
		}
	
		List<UserShipping> userShippings = user.getUserShippings();
		List<UserPayment> userPayments = user.getUserPayments();
		
		model.addAttribute("userShippings", userShippings);
		model.addAttribute("userPayments", userPayments);
		
		if (userPayments.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, false);
		}
		
		if (userShippings.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, false);
		}
		
		
		for(UserShipping userShipping : userShippings) {
			if(userShipping.isShippingDefault()) {
				shippingAddress = shippingAddressService.setByUserShipping(userShipping, shippingAddress);
				break;
			}
		}
		
		for (UserPayment userPayment : userPayments) {
			if(userPayment.isDefaultPayment()) {
				payment = paymentService.setByUserPayment(userPayment, payment);
				billingAddress = billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
				break;
			}
		}
		
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("shoppingCart", user.getShoppingCart());
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("classActiveShipping", true);
		
		return "checkout";
		
	}
	
	@GetMapping("/setShippingAddress/{id}")
	public String setShippingAddress(@PathVariable("id") int userShippingId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);
		
		if(userShipping.getUser().getUserId() != user.getUserId()) {
			return "badRequestPage";
		} 
			
		List<CartItem> cartItems = cartItemService.findByShoppingCart(user.getShoppingCart());
		
		shippingAddress = shippingAddressService.setByUserShipping(userShipping, shippingAddress);
		
		List<UserShipping> userShippings = user.getUserShippings();
		List<UserPayment> userPayments = user.getUserPayments();
		
		model.addAttribute("userShippings", userShippings);
		model.addAttribute("userPayments", userPayments);
		
		if (userPayments.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, false);
		}
		
		if (userShippings.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, false);
		}
		
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("shoppingCart", user.getShoppingCart());
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("classActiveShipping", true);
		
		return "checkout";
	}
	
	@GetMapping("/setPaymentMethod/{id}")
	public String setPaymentMethod(@PathVariable("id") int userPaymentId, Model model, Principal principal) {
		User user=userService.findByUsername(principal.getName());
		
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		UserBilling userBilling = userPayment.getUserBilling();
		
		if(userPayment.getUser().getUserId() != user.getUserId()) {
			return "badRequestPage";
		}
		
		payment = paymentService.setByUserPayment(userPayment, payment);
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(user.getShoppingCart());
		
		billingAddress = billingAddressService.setByUserBilling(userBilling, billingAddress);
		
		
		List<UserShipping> userShippings = user.getUserShippings();
		List<UserPayment> userPayments = user.getUserPayments();
		
		model.addAttribute("userShippings", userShippings);
		model.addAttribute("userPayments", userPayments);
		
		if (userPayments.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_PAYMENT_LIST, false);
		}
		
		if (userShippings.size() == 0) {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, true);
		} else {
			model.addAttribute(MessageConstants.EMPTY_SHIPPING_LIST, false);
		}
		
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("shoppingCart", user.getShoppingCart());
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("classActivePayment", true);
		
		return "checkout";
		
	}
	
}
