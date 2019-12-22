package com.luv2code.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.entity.User;
import com.luv2code.service.UserService;
import com.luv2code.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	 @Autowired
	 private UserService userService;
	 
	 private Logger logger = Logger.getLogger(getClass().getName());
	 
	 @InitBinder
	 public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	 }
	 
	 @GetMapping("/showRegistrationForm")
	 public String showMyLoginPage(Model model) {
		
		 model.addAttribute("crmUser", new CrmUser());
		
		return "registration-form";
	 }

	 @PostMapping("/processRegistrationForm")
	 public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser crmUser, 
				BindingResult result, 
				Model model) {
		
		String userName = crmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (result.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
        	model.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        // create user account        						
        userService.save(crmUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
