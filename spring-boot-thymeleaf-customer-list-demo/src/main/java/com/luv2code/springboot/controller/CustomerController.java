package com.luv2code.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private List<Customer> customers;
	
	@PostConstruct
	private void loadData() {
		Customer customer1=new Customer(1, "John", "Doe", "john@luv2code.com");
		Customer customer2=new Customer(2, "Mary", "Public", "mary@luv2code.com");
		Customer customer3=new Customer(3, "Susan", "Adams", "susan@luv2code.com");
		
		customers = new ArrayList<Customer>();
		
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
	}
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customers);
		
		return "customer-list";
	}
	
}
