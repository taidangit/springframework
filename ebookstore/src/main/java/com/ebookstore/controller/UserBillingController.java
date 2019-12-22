package com.ebookstore.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserBilling;
import com.ebookstore.domain.UserPayment;
import com.ebookstore.service.UserBillingService;
import com.ebookstore.service.UserPaymentService;
import com.ebookstore.service.UserService;
import com.ebookstore.utility.USConstants;

@Controller
public class UserBillingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserBillingService userBillingService;
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	@GetMapping("/listOfCreditCards")
	public String listOfCreditCards(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userShippings", user.getUserShippings());
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		
		model.addAttribute("userOrders", user.getUserOrders());
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
	
	@GetMapping("/addNewCreditCard")
	public String addNewCreditCard(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userShippings", user.getUserShippings());
		model.addAttribute("userOrders", user.getUserOrders());
		
		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		
		model.addAttribute("userBilling", new UserBilling());
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("userShippings", user.getUserShippings());
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
	
	@PostMapping("/processAddNewCreditCard")
	public String processAddNewCreditCard(
			@Valid @ModelAttribute("userBilling") UserBilling userBilling,
			BindingResult result,
			Principal principal,
			Model model) {
		
		User user = userService.findByUsername(principal.getName());
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("states", states);
			model.addAttribute("addNewCreditCard", true);
			model.addAttribute("classActiveBilling", true);
			
			model.addAttribute("userPayments", user.getUserPayments());
			model.addAttribute("userShippings", user.getUserShippings());
			model.addAttribute("userOrders", user.getUserOrders());
			model.addAttribute("listOfShippingAddresses", true);
			
			return "myProfile";
		}
		
		userBilling.getUserPayment().setDefaultPayment(true);
		userBilling.getUserPayment().setUser(user);
		
		userBilling.setUserPayment(userBilling.getUserPayment());
		
		userBillingService.save(userBilling);
	
		return "redirect:/listOfCreditCards";
			
	}
	
	@GetMapping("/updateCreditCard/{id}")
	public String updateCreditCard(@PathVariable("id") int userPaymentId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		if(user.getUserId() != userPayment.getUser().getUserId()) {
			return "badRequestPage";
		}
			
		model.addAttribute("user", user);
		UserBilling userBilling = userPayment.getUserBilling();
		model.addAttribute("userBilling", userBilling);
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		
		
		return "myProfile";
	}
	
	@GetMapping("/deleteCreditCard/{id}")
	public String deleteCreditCard(@PathVariable("id") int userPaymentId, Model model, Principal principal) {
		User user=userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		if(user.getUserId() != userPayment.getUser().getUserId()) {
			return "badRequestPage";
		} 
		
		userPaymentService.deleteById(userPaymentId);
		
		return "redirect:/listOfCreditCards";
	
	}
	
	@GetMapping("/setDefaultPayment/{id}")
	public String setDefaultPayment(@PathVariable("id") int userPaymentId, Model model, Principal principal ) {
		User user=userService.findByUsername(principal.getName());
		userPaymentService.setUserDefaultPayment(userPaymentId, user);
		
		return "redirect:/listOfCreditCards";
	}
}
