package com.luv2code.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class CreateDemo {

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
			
//			Instructor instructor=new Instructor("Madhu", "Patel", "madhu@luv2code.com");
//			InstructorDetail instructorDetail=new InstructorDetail("http://www.youtube.com", "Guitar");
//			instructor.setInstructorDetail(instructorDetail);
			
			Instructor instructor=new Instructor("Chad", "Darby", "darby@luv2code.com");
			InstructorDetail instructorDetail=new InstructorDetail("http://www.youtube.com", "Luv2 code!!!");
			instructor.setInstructorDetail(instructorDetail);
			
			session.save(instructor);
			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
