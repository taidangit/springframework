package com.luv2code.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.luv2code.springboot.entity.Customer;

@RepositoryRestResource(path="members")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
