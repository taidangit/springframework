package com.luv2code.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luv2code.springboot.dao.CustomerRepository;
import com.luv2code.springboot.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int theId) {
		return customerRepository.findById(theId).get();
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);
	}

}
