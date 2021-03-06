package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Student;

public class DeletePacmanCourseDemo {

	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course course = session.get(Course.class, 28);
			if(course != null) {
				session.delete(course);
			}
			
		
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
	
}
