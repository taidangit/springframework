package com.dangphattai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dangphattai.entity.Role;
import com.dangphattai.entity.User;
import com.dangphattai.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/showRegistrationForm")
	public String register(Model model) {
		
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("user") User user, 
			BindingResult bindingResult,
			@RequestParam("repeat-pass") String repeatPassword,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "register";	
		}
		
		
		if(!isUserExist(user.getUsername())) {
			
			if(user.getPassword().equals(repeatPassword)) {
				
				Role role = new Role();
				role.setRoleId(1);
				
				user.setRole(role);
				
				if(userService.saveUser(user) != null) {
					model.addAttribute("msgsuccess", "Register successfully!");
				} 
			} else {
				model.addAttribute("msgerror", "Password do not match");
			}
		} else {
			model.addAttribute("msgerror", "Username already exists.");
		}
		
		return "register";
	}
	
	private boolean isUserExist(String username) {
		
		return userService.checkUserByUsername(username);
	}
}
