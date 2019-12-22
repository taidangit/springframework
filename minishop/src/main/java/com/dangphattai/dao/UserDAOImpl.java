package com.dangphattai.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangphattai.entity.Product;
import com.dangphattai.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean checkUser(String username, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			Query<User> theQuery = 
					currentSession.createQuery("from User where username=:username and password=:password",
							User.class);
			theQuery.setParameter("username", username);
			theQuery.setParameter("password", password);
			
			User user = theQuery.getSingleResult();
			if(user != null) return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int getRoleId(String username, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			Query theQuery = 
					currentSession.createQuery("select role.roleId from User where username=:username and password=:password");
			theQuery.setParameter("username", username);
			theQuery.setParameter("password", password);
			
			int roleId = (int) theQuery.getSingleResult();
			
			return roleId;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public boolean checkUserByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			Query<User> theQuery = 
					currentSession.createQuery("from User where username=:username",
					User.class);
			
			theQuery.setParameter("username", username);
			User user = theQuery.getSingleResult();
			
			if(user != null) return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(user);
		return user;
	}

}
