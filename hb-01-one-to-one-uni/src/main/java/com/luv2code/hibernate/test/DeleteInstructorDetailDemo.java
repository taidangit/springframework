package com.luv2code.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
	
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
			
			InstructorDetail instructorDetail=session.get(InstructorDetail.class, 4);
			if(instructorDetail != null) {
				instructorDetail.getInstructor().setInstructorDetail(null);
				session.delete(instructorDetail);
			}
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
}
