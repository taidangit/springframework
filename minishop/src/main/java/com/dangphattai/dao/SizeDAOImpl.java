package com.dangphattai.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangphattai.entity.Size;

@Repository
@Transactional
public class SizeDAOImpl implements SizeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Size> getSizes() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Size> theQuery = currentSession.createQuery("from Size", Size.class);
		List<Size> sizes = theQuery.getResultList();
		
		return sizes;
	}

	
}
