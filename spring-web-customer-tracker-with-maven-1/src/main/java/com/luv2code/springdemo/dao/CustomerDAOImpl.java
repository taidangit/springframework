package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	public List<Customer> getCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
				
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
				
		return customers;
	}

	public void saveCustomer(Customer theCustomer) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		
	}

	public Customer getCustomer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	public void deleteCustomer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		/*Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();*/
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
	}

	
	public List<Customer> searchCustomers(String theSearchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer>theQuery = null;
			
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			theQuery =currentSession.createQuery("from Customer where firstName like :theName or lastName like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.trim() + "%");

		} else {
			theQuery =currentSession.createQuery("from Customer", Customer.class);			
		}
				
		List<Customer> customers = theQuery.getResultList();		
		
		return customers;
	}

}











