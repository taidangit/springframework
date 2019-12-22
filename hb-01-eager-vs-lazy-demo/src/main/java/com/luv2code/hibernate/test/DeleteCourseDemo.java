package com.luv2code.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Course course = session.get(Course.class, 4);
			if (course != null) {
				session.delete(course);
			}

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
}
