package com.luv2code.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.entity.Messages;

@Repository
@Transactional
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Messages> getMessages() {
		Session session = sessionFactory.getCurrentSession();
		Query<Messages> query = 
				session.createQuery("from Message", Messages.class);
		
		List<Messages> messages = query.getResultList();
				
		return messages;
	}

	@Override
	public Messages getMessageById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Messages message = session.get(Messages.class, id);
		
		return message;
	}

	@Override
	public void saveMessage(Messages message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
		
	}

	@Override
	public void deleteMessage(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = 
				session.createQuery("delete from Message where messageId=:id");
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
}
