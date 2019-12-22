package com.luv2code.springdemo.controller;

import javax.validation.Valid;

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

import com.luv2code.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	@PostMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theResult, Model theModel) {
		System.out.println("Loi: "+theResult);
		if(theResult.hasErrors()) {
			return "customer-form";
		}
		System.out.println("|"+theCustomer.getFirstName()+"|");
		System.out.println("|"+theCustomer.getLastName()+"|");
		theModel.addAttribute("customer", theCustomer);
		return "customer-confirmation";
	}
}
