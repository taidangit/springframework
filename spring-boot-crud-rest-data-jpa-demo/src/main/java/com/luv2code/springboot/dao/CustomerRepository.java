package com.luv2code.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
