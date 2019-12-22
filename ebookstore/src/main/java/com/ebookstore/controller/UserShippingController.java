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
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserShipping;
import com.ebookstore.service.UserService;
import com.ebookstore.service.UserShippingService;
import com.ebookstore.utility.USConstants;

@Controller
@RequestMapping("/userShipping")
public class UserShippingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@GetMapping("/list")
	public String listOfShippingAddresses(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userShippings", user.getUserShippings());
		model.addAttribute("userOrders", user.getUserOrders());
		model.addAttribute("listOfCreditCards", true);
		
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
	
	@GetMapping("/add")
	public String addNewShippingAddress(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userShippings", user.getUserShippings());
		model.addAttribute("userOrders", user.getUserOrders());
		
		model.addAttribute("listOfCreditCards", true);
		
		model.addAttribute("addNewShippingAddress", true);
		model.addAttribute("classActiveShipping", true);

		model.addAttribute("userShipping", new UserShipping());
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		return "myProfile";
	}
	
	@PostMapping("/save")
	public String processAddNewShippingAddress(@Valid @ModelAttribute("userShipping") UserShipping userShipping,
			BindingResult result, Model model, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("states", states);
			model.addAttribute("addNewShippingAddress", true);
			model.addAttribute("classActiveShipping", true);
			
			model.addAttribute("userPayments", user.getUserPayments());
			model.addAttribute("userShippings", user.getUserShippings());
			model.addAttribute("userOrders", user.getUserOrders());
			model.addAttribute("listOfCreditCards", true);
			
			return "myProfile";
		}
		
		model.addAttribute("user", user);
		
		userShipping.setShippingDefault(true);
		userShipping.setUser(user);
		
		userShippingService.save(userShipping);
		
		return "redirect:/userShipping/list";
	}
	
	@GetMapping("/update/{id}")
	public String updateShippingAddress(@PathVariable("id") int userShippingId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);
		if(user.getUserId() != userShipping.getUser().getUserId()) {
			return "badRequestPage";
		} 
			
		model.addAttribute("user", user);
		model.addAttribute("userShipping", userShipping);
		
		List<String> states = USConstants.listOfUSStatesCode;
		Collections.sort(states);
		model.addAttribute("states", states);
		
		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userShippings", user.getUserShippings());
		model.addAttribute("userOrders", user.getUserOrders());
		model.addAttribute("listOfCreditCards", true);
		
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("addNewShippingAddress", true);
		
		
		return "myProfile";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUserShipping(@PathVariable("id") int userShippingId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);
		if(user.getUserId() != userShipping.getUser().getUserId()) {
			return "badRequestPage";
		}

		userShippingService.deleteById(userShippingId);;
		
		return "redirect:/userShipping/list";
	
	}
	
	@GetMapping("/setDefaultShippingAddress/{id}")
	public String setDefaultShippingAddress(@PathVariable("id") int userShippingId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		userShippingService.setUserDefaultShipping(userShippingId, user);
		
		return "redirect:/userShipping/list";
	}
}
