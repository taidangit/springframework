package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Student;

public class DeleteMaryStudentDemo {

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
			
			Student student=session.get(Student.class, 15);
			if(student != null) {
				session.delete(student);
			}
			
		
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
	
}
