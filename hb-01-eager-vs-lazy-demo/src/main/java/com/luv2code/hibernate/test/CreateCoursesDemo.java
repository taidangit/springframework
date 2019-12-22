package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

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

			Instructor instructor = session.get(Instructor.class, 5);
			if (instructor != null) {

				Course course = new Course("Air Guitar- The Ultimate Guide");
				Course course2 = new Course("The Pinball MasterClass");

				course.setInstructor(instructor);
				course2.setInstructor(instructor);
			
				session.save(course);
				session.save(course2);
			}

			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
}
