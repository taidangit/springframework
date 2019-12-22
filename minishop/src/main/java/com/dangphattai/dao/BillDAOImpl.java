package com.dangphattai.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangphattai.entity.Bill;

@Repository
@Transactional
public class BillDAOImpl implements BillDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Bill addBill(Bill bill) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(bill);
		
		return bill;
	}
}
