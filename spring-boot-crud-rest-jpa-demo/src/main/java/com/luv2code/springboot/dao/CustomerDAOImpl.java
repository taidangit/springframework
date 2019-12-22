package com.luv2code.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.entity.Customer;


@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Customer> findAll() {
		
		Query theQuery= entityManager.createQuery("from Customer");

		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public Customer findById(int theId) {

		Customer theCustomer = entityManager.find(Customer.class, theId);

		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		Customer dbCustomer=entityManager.merge(theCustomer);
		theCustomer.setId(dbCustomer.getId());

	}

	@Override
	public void deleteById(int theId) {
		Customer theCustomer = entityManager.find(Customer.class, theId);
		
//		Query theQuery = 
//				entityManager.createQuery("delete from Customer where id=:customerId");
//			theQuery.setParameter("customerId", theId);
//
//			theQuery.executeUpdate();

		entityManager.remove(theCustomer);
	}

}
