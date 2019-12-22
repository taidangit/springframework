package com.luv2code.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.entity.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public Role findRoleByName(String roleName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", roleName);
		
		Role role = null;
		
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		
		return role;
	}

}
