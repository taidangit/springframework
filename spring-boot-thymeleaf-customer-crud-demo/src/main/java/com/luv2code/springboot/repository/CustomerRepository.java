package com.luv2code.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findAllByOrderByLastNameDesc();
}
