package com.luv2code.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i JOIN FETCH i.courses where i.instructorId=: instructorId",
					Instructor.class);
			query.setParameter("instructorId", 5);
			
			Instructor instructor = query.getSingleResult();
			
			System.out.println(instructor);
			
			System.out.println(instructor.getCourses());
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

}
