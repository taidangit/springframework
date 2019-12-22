package com.luv2code.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class DeleteDemo {
	
	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
		
			session.beginTransaction();
			
			Instructor instructor=session.get(Instructor.class, 1);
			if(instructor != null) {
				session.delete(instructor);
			}
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

}
