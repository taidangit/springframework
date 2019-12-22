package com.luv2code.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.dao.CustomerDAO;
import com.luv2code.springboot.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public Customer findById(int theId) {
		return customerDAO.findById(theId);
	}

	@Override
	public void save(Customer customer) {
		customerDAO.save(customer);

	}

	@Override
	public void deleteById(int theId) {
		customerDAO.deleteById(theId);
	}

}
