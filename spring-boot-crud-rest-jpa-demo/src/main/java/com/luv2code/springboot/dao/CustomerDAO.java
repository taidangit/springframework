package com.luv2code.springboot.dao;

import java.util.List;

import com.luv2code.springboot.entity.Customer;

public interface CustomerDAO {
	
	List<Customer> findAll();
	Customer findById(int theId);
	void save(Customer theCustomer);
	void deleteById(int theId);
	

}
