package com.dangphattai.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangphattai.entity.Color;

@Repository
@Transactional
public class ColorDAOImpl implements ColorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Color> getColors() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Color> theQuery = currentSession.createQuery("from Color", Color.class);
		List<Color> colors = theQuery.getResultList();
		
		return colors;
	}
	
	
}
