package com.luv2code.springboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.entity.Customer;
import com.luv2code.springboot.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> listCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.findById(customerId);
		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		return theCustomer;

	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		customerService.save(theCustomer);
		return theCustomer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.save(theCustomer);

		return theCustomer;
	}

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.findById(customerId);
		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}
		customerService.deleteById(customerId);

		return "Deleted customer id - " + customerId;
	}

}
