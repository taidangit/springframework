package com.ebookstore.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebookstore.domain.PasswordResetToken;
import com.ebookstore.domain.Role;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.service.RoleService;
import com.ebookstore.service.UserDetailsServiceImpl;

import com.ebookstore.service.UserService;
import com.ebookstore.utility.MailConstructor;
import com.ebookstore.utility.MessageConstants;
import com.ebookstore.utility.SecurityUtility;

@Controller
public class UserController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@GetMapping("/myProfile")
	public String myProfile(Model model, Principal principal, HttpSession session) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("classActiveEdit", true);
		
		model.addAttribute("userPayments", user.getUserPayments());
		model.addAttribute("userOrders", user.getUserOrders());
		model.addAttribute("userShippings", user.getUserShippings());
		
		if(session.getAttribute(MessageConstants.UPDATE_USER_INFO_SUCCESS) != null) {
			model.addAttribute(MessageConstants.UPDATE_USER_INFO_SUCCESS,
					session.getAttribute(MessageConstants.UPDATE_USER_INFO_SUCCESS));
			session.removeAttribute(MessageConstants.UPDATE_USER_INFO_SUCCESS);
		}
		return "myProfile";
	}
	
	@PostMapping("/forgetPassword") 
	public String forgetPassword(HttpServletRequest request,
			@RequestParam String email, Model model) {
		
		model.addAttribute("user", new User());
		
		User user = userService.findByEmail(email);
		if(user == null) {
			model.addAttribute("emailNotExist", true);
			model.addAttribute("classActiveForgetPassword", true); 
			return "myAccount";
		}
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetToken(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(newEmail);
		
		model.addAttribute("classActiveForgetPassword", true); 
		model.addAttribute("forgetPasswordEmailSent", true);
		
		return "myAccount"; 
	 
	 }
	
	@GetMapping("/newUser") 
	public String newUser(@RequestParam("token") String token, Model model) {
		PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
		if(passwordResetToken == null) {
			model.addAttribute("message", "Invalid token.");
			return "redirect:/badRequest";
		}
		
		User user = passwordResetToken.getUser();
		
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		model.addAttribute("user", user); 
		model.addAttribute("classActiveEdit", true); 
		
		return "myProfile"; 
	 
	 }
	
	@PostMapping("/processNewUser")
	public String processNewUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			model.addAttribute("classActiveNewAccount", true); 
			return "myAccount";
		}
		
		if(userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("usernameExists", true);
			model.addAttribute("classActiveNewAccount", true); 
			return "myAccount";
		}
		
		if(userService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("emailExists", true);
			model.addAttribute("classActiveNewAccount", true); 
			return "myAccount";
		}
		
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findByName("ROLE_USER"));
		user.setRoles(roles);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		user.setShoppingCart(shoppingCart);
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetToken(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage emailSend = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(emailSend);
		
		model.addAttribute("emailSent", true);
		model.addAttribute("classActiveNewAccount", true); 
		
		return "myAccount";
	}
	
	@PostMapping("/updateUserInfo")
	public String updateUserInfo(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam String newPassword,
			@RequestParam String cofirmPassword,
			Model model, Principal principal, HttpSession session) {
		
		if(result.hasErrors()) {
			model.addAttribute("classActiveEdit", true);
			return "myProfile";
			
		}
		
		User currentUser = userService.findById(user.getUserId());

		if (userService.findByEmail(user.getEmail()) != null) {
			if(userService.findByEmail(user.getEmail()).getUserId() != currentUser.getUserId()) {
				model.addAttribute("emailExists", true);
				model.addAttribute("classActiveEdit", true);
				return "myProfile";
			}
		}
		
		if (userService.findByUsername(user.getUsername())!=null) {
			if(userService.findByUsername(user.getUsername()).getUserId() != currentUser.getUserId()) {
				model.addAttribute("usernameExists", true);
				model.addAttribute("classActiveEdit", true);
				return "myProfile";
			}
		}
		
		BCryptPasswordEncoder encryptedPassword = SecurityUtility.passwordEncoder();
		if(encryptedPassword.matches(user.getPassword(), currentUser.getPassword())) {
			if(newPassword.equals(cofirmPassword)) {
				user.setPassword(encryptedPassword.encode(newPassword));
			} else {
				model.addAttribute("notMatchPassword", true);
				model.addAttribute("classActiveEdit", true);
				return "myProfile";
			}
		} else {
			model.addAttribute("incorrectPassword", true);
			model.addAttribute("classActiveEdit", true);
			return "myProfile";
		}
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findByName("ROLE_USER"));
		user.setRoles(roles);
		userService.save(user);
		
		session.setAttribute(MessageConstants.UPDATE_USER_INFO_SUCCESS, true);
		model.addAttribute("classActiveEdit", true);

		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder .getContext().setAuthentication(authentication);
		
		return "redirect:/myProfile";
	}
	
}
