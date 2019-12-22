package com.ebookstore.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.ebookstore.utility.MessageConstants;

@Controller
public class UserOrderController {

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
	
	@PostMapping("/order")
	public String order(@ModelAttribute Payment payment,
			@ModelAttribute BillingAddress billingAddress,
			@ModelAttribute ShippingAddress shippingAddress,
			@RequestParam(required = false) String billingSameAsShipping,
			@RequestParam String shippingMethod,
			Model model, Principal principal, HttpSession session) {
		User user = userService.findByUsername(principal.getName());
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		if(billingSameAsShipping != null) {
			billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
			billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
			billingAddress.setBillingAddressName(shippingAddress.getShippingAddressName());
			billingAddress.setBillingAddressStreet(shippingAddress.getShippingAddressStreet());
			billingAddress.setBillingAddressState(shippingAddress.getShippingAddressState());
			billingAddress.setBillingAddressZipcode(shippingAddress.getShippingAddressZipcode());
		}
		
		if(shippingAddress.getShippingAddressCity().isEmpty() ||
				shippingAddress.getShippingAddressName().isEmpty() ||
				shippingAddress.getShippingAddressCountry().isEmpty() ||
				shippingAddress.getShippingAddressStreet().isEmpty() ||
				shippingAddress.getShippingAddressState().isEmpty() ||
				shippingAddress.getShippingAddressZipcode().isEmpty() ||
				payment.getCardName().isEmpty() ||
				payment.getType().isEmpty() ||
				payment.getHolderName().isEmpty() ||
				payment.getCardNumber().isEmpty() ||
				payment.getCvc()==0 ||
				billingAddress.getBillingAddressName().isEmpty() ||
				billingAddress.getBillingAddressCity().isEmpty() ||
				billingAddress.getBillingAddressCountry().isEmpty() ||
				billingAddress.getBillingAddressName().isEmpty() ||
				billingAddress.getBillingAddressState().isEmpty() ||
				billingAddress.getBillingAddressStreet().isEmpty() ||
				billingAddress.getBillingAddressZipcode().isEmpty()) {
			
			session.setAttribute(MessageConstants.MISSING_REQUIRED_FIELD, true);
			
			return "redirect:/checkout/"+shoppingCart.getShoppingCartId();
		}
		
		
		UserOrder userOrder = userOrderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);
		List<CartItem> cartItems = cartItemService.findByUserOrder(userOrder);
		model.addAttribute("cartItems", cartItems);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, userOrder, cartItems, Locale.ENGLISH));
		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		LocalDate estimatedDeliveryDate;
		
		if(shippingMethod.equals("groundShipping")) {
			estimatedDeliveryDate = LocalDate.now().plusDays(5);
		} else {
			estimatedDeliveryDate = LocalDate.now().plusDays(3);
		}
		
		model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);
		
		return "orderSubmitted";
	}
	
	@GetMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable("id") int orderId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		UserOrder order = userOrderService.findById(orderId);
		if(order.getUser().getUserId() != user.getUserId()) {
			return "badRequestPage";
		}
		
		model.addAttribute("order", order);
		
		List<CartItem> cartItems = order.getCartItems();
		model.addAttribute("cartItems", cartItems);
		
		model.addAttribute("userOrders", user.getUserOrders());
		model.addAttribute("classActiveOrders", true);
		model.addAttribute("displayOrderDetail", true);
		
		return "myProfile";
		
	}
}
