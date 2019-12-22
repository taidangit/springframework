package com.luv2code.springboot.service;

import java.util.List;

import com.luv2code.springboot.entity.Customer;

public interface CustomerService {
	
	List<Customer> findAll();
	
	Customer findById(int theId);
	
	void save(Customer customer);
	
	void deleteById(int theId);
}
