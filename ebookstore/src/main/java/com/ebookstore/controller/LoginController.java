package com.ebookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ebookstore.domain.User;

@Controller
public class LoginController {

	
	@GetMapping("/login") 
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true); 
		model.addAttribute("user", new User());
		return "myAccount"; 
	 
	 }
	 
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
